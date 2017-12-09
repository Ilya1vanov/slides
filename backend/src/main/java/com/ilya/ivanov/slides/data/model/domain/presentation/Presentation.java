package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.google.common.collect.Lists;
import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;
import com.ilya.ivanov.slides.utils.time.TimeUtils;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

import static com.ilya.ivanov.slides.constants.SearchConstants.fullTextAnalyzer;
import static com.ilya.ivanov.slides.constants.SearchConstants.tagsAnalyzer;
import static com.ilya.ivanov.slides.data.model.domain.presentation.Presentation.TABLE_KEY;
import static java.util.stream.Collectors.toList;

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
    public static final String MODIFICATION_DATE_KEY = "modification_date";
    public static final String TAGS_TABLE_KEY = "tags";
    public static final String TAGS_ID_KEY = "tag_id";
    public static final String USER_KEY = "owner_id";
    public static final String USER_FIELD = "owner";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_KEY)
    private Long id;

    @Column(name = TITLE_KEY)
    @NonNull
    @Field
    @Analyzer(definition = fullTextAnalyzer)
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

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = TAGS_TABLE_KEY, joinColumns = @JoinColumn(name = TAGS_ID_KEY))
    @IndexedEmbedded
    @Field
    @Analyzer(definition = tagsAnalyzer)
    private Collection<String> tags = Lists.newArrayList();

    private Presentation(Long id, @NonNull String title, @NonNull Collection<String> tags) {
        this(title);
        this.id = id;
        this.tags = tags;
    }

    @PreUpdate
    private void preUpdate() {
        this.modificationDate = TimeUtils.now();
    }

    public Presentation merge(PresentationDto presentationDto) {
        this.title = presentationDto.getTitle();
        this.tags = presentationDto.getTags();
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
        val tags = this.getTags();
        return new PresentationDto(id, owner, title, creationDate, modificationDate, tags, slidesIds, link);
    }

    @NotNull
    public static Presentation fromDto(PresentationDto presentationDto) {
        val id = presentationDto.getId();
        val title = presentationDto.getTitle();
        val tags = presentationDto.getTags();
        return new Presentation(id, title, tags);
    }
}
