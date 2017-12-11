package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;
import com.ilya.ivanov.slides.utils.time.TimeUtils;
import lombok.*;
import org.hibernate.search.annotations.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static com.ilya.ivanov.slides.constants.SearchConstants.FULL_TEXT_ANALYZER;
import static com.ilya.ivanov.slides.data.model.domain.presentation.Presentation.TABLE_KEY;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table(name = TABLE_KEY)
@Indexed
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class Presentation {
    public static final String TABLE_KEY = "presentation";

    public static final String ID_KEY = "id";
    public static final String TITLE_KEY = "title";
    public static final String CREATION_DATE_KEY = "creation_date";
    public static final String CREATION_DATE_FIELD = "creationDate";
    public static final String MODIFICATION_DATE_KEY = "modification_date";
    public static final String MODIFICATION_DATE_FIELD = "modificationDate";
    public static final String TAGS_TABLE_KEY = "presentation_tags";
    public static final String TAGS_PRESENTATION_ID_KEY = "presentation_id";
    public static final String TAGS_TAG_ID_KEY = "tag_id";
    public static final String TAGS_FIELD_KEY = "searchTags";
    public static final String USER_KEY = "owner_id";
    public static final String USER_FIELD = "owner";

    public static final String TAGS_AUTOCOMPLETE_KEY = "searchTags-autocomplete";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_KEY)
    private Long id;

    @Column(name = TITLE_KEY)
    @NonNull
    @Field
    @Analyzer(definition = FULL_TEXT_ANALYZER)
    private String title;

    @Column(name = CREATION_DATE_KEY)
    @Temporal(TemporalType.TIMESTAMP)
    @Field(store = Store.YES, analyze = Analyze.NO)
    private Date creationDate = TimeUtils.now();

    @Column(name = MODIFICATION_DATE_KEY)
    @Temporal(TemporalType.TIMESTAMP)
    @Field(store = Store.YES, analyze = Analyze.NO)
    private Date modificationDate = TimeUtils.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = USER_KEY)
    @IndexedEmbedded
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Slide> slides = Lists.newArrayList();

    @OneToOne(cascade = CascadeType.ALL)
    private ShareLink shareLink;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = TAGS_TABLE_KEY, joinColumns
            = @JoinColumn(name = TAGS_PRESENTATION_ID_KEY,
            referencedColumnName = ID_KEY),
            inverseJoinColumns = @JoinColumn(name = TAGS_TAG_ID_KEY,
                    referencedColumnName = SearchTag.ID_KEY))
    @IndexedEmbedded
    private Set<SearchTag> searchTags = Sets.newHashSet();

    private Presentation(Long id, @NonNull String title) {
        this(title);
        this.id = id;
    }

    @PreUpdate
    private void preUpdate() {
        this.modificationDate = TimeUtils.now();
    }

    public Presentation merge(PresentationDto presentationDto) {
        this.title = presentationDto.getTitle();
        if (this.shareLink != null) {
            this.shareLink.merge(presentationDto.getShareLink());
        }
        return this;
    }

    @NotNull
    public PresentationDto toDto() {
        val id = this.getId();
        val owner = this.getOwner().getUsername();
        val title = this.getTitle();
        val creationDate = this.getCreationDate().getTime();
        val modificationDate = this.getCreationDate().getTime();
        val slidesIds = this.getSlides().stream().map(Slide::getId).collect(toList());
        val shareLink = this.getShareLink();
        String link = shareLink != null ? shareLink.getLink() : null;
        val tags = this.getSearchTags().stream().map(SearchTag::toDto).collect(toSet());
        return new PresentationDto(id, owner, title, creationDate, modificationDate, tags, slidesIds, link);
    }

    @NotNull
    public static Presentation fromDto(PresentationDto presentationDto) {
        val id = presentationDto.getId();
        val title = presentationDto.getTitle();
        return new Presentation(id, title);
    }
}
