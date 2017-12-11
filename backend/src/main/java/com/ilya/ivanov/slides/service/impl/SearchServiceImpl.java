package com.ilya.ivanov.slides.service.impl;

import com.google.common.collect.Lists;
import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import com.ilya.ivanov.slides.data.model.domain.presentation.SearchTag;
import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;
import com.ilya.ivanov.slides.data.model.dto.search.SearchResults;
import com.ilya.ivanov.slides.data.model.dto.user.UserDto;
import com.ilya.ivanov.slides.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.lucene.search.Query;
import org.hibernate.search.exception.EmptyQueryException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;

import static com.ilya.ivanov.slides.constants.SearchConstants.DATE_FORMAT;
import static com.ilya.ivanov.slides.constants.SearchConstants.DATE_RANGE_SEPARATOR;
import static java.util.stream.Collectors.toList;

/**
 * Created by i.ivanov on 11/27/17.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public final class SearchServiceImpl implements SearchService {

    private final EntityManagerFactory entityManagerFactory;

    private static final String[] USER_SEARCH_FIELDS = {User.USERNAME_KEY, User.FIRST_NAME_FIELD, User.LAST_NAME_FIELD};

    private static final String[] PRESENTATION_SEARCH_FIELDS = {
            Presentation.TITLE_KEY,
            Presentation.USER_FIELD + "." + User.USERNAME_KEY,
            Presentation.TAGS_FIELD_KEY + "." + SearchTag.TAG_KEY
    };

    private static final String PRESENTATION_CREATION_DATE_FIELD = Presentation.CREATION_DATE_FIELD;

    private static final String PRESENTATION_MODIFICATION_DATE_FIELD = Presentation.MODIFICATION_DATE_FIELD;

    @NotNull
    @Override
    public SearchResults fullTextSearch(String request) {
        val em = entityManagerFactory.createEntityManager();
        val fullTextEntityManager = Search.getFullTextEntityManager(em);
        em.getTransaction().begin();

        val users = searchInUsers(fullTextEntityManager, request);
        val presentations = searchInPresentations(fullTextEntityManager, request);

        em.getTransaction().commit();
        em.close();
        return new SearchResults(users, presentations);
    }

    private Collection<PresentationDto> searchInPresentations(FullTextEntityManager fullTextEntityManager, String request) {
        Function<QueryBuilder, Query> builderQueryFunction = buildQueryInPresentations(request);
        return getResults(fullTextEntityManager, Presentation.class, builderQueryFunction)
                .stream().map(Presentation::toDto).collect(toList());
    }

    @NotNull
    private Function<QueryBuilder, Query> buildQueryInPresentations(String request) {
        val requestDates = parseDateRange(request);
        if (requestDates.isEmpty() || requestDates.size() > 2) {
            return qb -> qb.keyword()
                    .onFields(PRESENTATION_SEARCH_FIELDS)
                    .matching(request)
                    .createQuery();
        }
        return buildQueryByDateRangeInPresentations(requestDates);
    }

    @NotNull
    private Function<QueryBuilder, Query> buildQueryByDateRangeInPresentations(List<Date> dates) {
        val from = dates.get(0).getTime();
        val to = getToDate(dates).getTime();
        return qb -> qb.range()
                .onField(PRESENTATION_CREATION_DATE_FIELD)
                .andField(PRESENTATION_MODIFICATION_DATE_FIELD)
                .ignoreFieldBridge()
                .from(from)
                .to(to).createQuery();
    }

    @NotNull
    private Date getToDate(List<Date> dates) {
        val start = dates.size() == 2 ? dates.get(1) : dates.get(0);
        val instance = Calendar.getInstance();
        instance.setTime(start);
        instance.add(Calendar.DAY_OF_YEAR, 1);
        return instance.getTime();
    }

    private List<Date> parseDateRange(String request) {
        val optionalDates = Arrays.stream(request.split(DATE_RANGE_SEPARATOR))
                .map(this::getDate).collect(toList());
        if (!optionalDates.stream().allMatch(Optional::isPresent)) {
            return Lists.newArrayList();
        }
        return optionalDates.stream().limit(2).map(Optional::get).collect(toList());
    }

    private Optional<Date> getDate(String request) {
        try {
            return Optional.of(DATE_FORMAT.parse(request));
        } catch (ParseException ignored) {
            return Optional.empty();
        }
    }

    private Collection<UserDto> searchInUsers(FullTextEntityManager fullTextEntityManager, String request) {
        final Function<QueryBuilder, Query> qbf = qb -> qb.keyword().onFields(USER_SEARCH_FIELDS).matching(request).createQuery();
        return getResults(fullTextEntityManager, User.class, qbf)
                .stream().map(User::toDto).collect(toList());
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> getResults(FullTextEntityManager fullTextEntityManager, Class<T> tClass, Function<QueryBuilder, Query> qbf) {
        try {
            val qb = fullTextEntityManager.getSearchFactory()
                    .buildQueryBuilder().forEntity(tClass).get();
            val luceneQuery = qbf.apply(qb);
            val jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, tClass);
            return jpaQuery.getResultList();
        } catch (EmptyQueryException ignore) {
            return Lists.newArrayList();
        }
    }
}
