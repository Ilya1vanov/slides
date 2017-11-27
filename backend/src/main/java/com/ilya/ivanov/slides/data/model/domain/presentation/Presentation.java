package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.google.common.collect.Lists;
import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;
import lombok.*;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table(name = "presentation")
@Indexed
@AnalyzerDef(name = "slides-analyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "English")
                })
        })
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @NonNull
    @Field
    @Analyzer(definition = "slides-analyzer")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    @IndexedEmbedded
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Slide> slides = Lists.newArrayList();

    @OneToOne(cascade = CascadeType.ALL)
    private ShareLink shareLink;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "tag_id"))
    @IndexedEmbedded
    @Field(name = "tags", index = Index.YES)
    private Collection<String> tags = Lists.newArrayList();

    private Presentation(Long id, @NonNull String title, @NonNull Collection<String> tags) {
        this(title);
        this.id = id;
        this.tags = tags;
    }

    public Presentation merge(PresentationDto presentationDto) {
        this.title = presentationDto.getTitle();
        this.tags = presentationDto.getTags();
        if (this.shareLink != null) {
            this.shareLink.merge(presentationDto.getShareLink());
        }
        return this;
    }

    public PresentationDto toDto() {
        val id = this.getId();
        val owner = this.getOwner().getUsername();
        val title = this.getTitle();
        val slidesIds = this.getSlides().stream().map(Slide::getId).collect(toList());
        val shareLink = this.getShareLink();
        String link = shareLink != null ? shareLink.getLink() : null;
        val tags = this.getTags();
        return new PresentationDto(id, owner, title, tags, slidesIds, link);
    }

    public static Presentation fromDto(PresentationDto presentationDto) {
        val id = presentationDto.getId();
        val title = presentationDto.getTitle();
        val tags = presentationDto.getTags();
        return new Presentation(id, title, tags);
    }
}
