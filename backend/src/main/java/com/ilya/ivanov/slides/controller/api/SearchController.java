package com.ilya.ivanov.slides.controller.api;

import com.google.common.collect.Lists;
import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.model.dto.search.SearchResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.search.exception.EmptyQueryException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by i.ivanov on 11/25/17.
 */
@RestController
@RequestMapping(value = "/api/search")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "/api/search")
public class SearchController {
    private final EntityManagerFactory entityManagerFactory;

    private static final String[] USER_SEARCH_FIELDS = {"username", "firstName", "lastName"};

    private static final String[] PRESENTATION_SEARCH_FIELDS = {"title", "owner.username", "tags"};

    @PostMapping("/")
    @ApiOperation(response = SearchResults.class, responseContainer = "List", value = "Search results returned")
    @ApiResponse(code = SC_OK, message = "Successful search")
    public SearchResults fullTextSearch(@ApiParam @RequestParam String request) {
        val em = entityManagerFactory.createEntityManager();
        val fullTextEntityManager = Search.getFullTextEntityManager(em);
        em.getTransaction().begin();

        val users = getResults(fullTextEntityManager, User.class, USER_SEARCH_FIELDS, request)
                .stream().map(User::toDto).collect(toList());

        val presentations = getResults(fullTextEntityManager, Presentation.class, PRESENTATION_SEARCH_FIELDS, request)
                .stream().map(Presentation::toDto).collect(toList());

        em.getTransaction().commit();
        em.close();
        return new SearchResults(users, presentations);
    }

    private <T> List<T> getResults(FullTextEntityManager fullTextEntityManager, Class<T> tClass, String[] fields, String request) {
        try {

            val qb = fullTextEntityManager.getSearchFactory()
                    .buildQueryBuilder().forEntity(tClass).get();
            val luceneQuery = qb.keyword().onFields(fields).matching(request).createQuery();
            val jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, tClass);
            return jpaQuery.getResultList();
        } catch (EmptyQueryException ignore) {
            return Lists.newArrayList();
        }
    }
}
