package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table(name = "presentation")
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
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id")
    @Getter(onMethod = @__(@JsonIgnore))
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Slide> slides = Lists.newArrayList();

    @OneToOne(cascade = CascadeType.ALL)
    private ShareLink shareLink;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name = "tag", joinColumns = @JoinColumn(name = "tag_id"))
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

    public static Presentation fromDto(PresentationDto presentationDto) {
        val id = presentationDto.getId();
        val title = presentationDto.getTitle();
        val tags = presentationDto.getTags();
        return new Presentation(id, title, tags);
    }

    public static PresentationDto toDto(Presentation presentation) {
        val id = presentation.getId();
        val title = presentation.getTitle();
        val slidesIds = presentation.getSlides().stream().map(Slide::getId).collect(toList());
        val shareLink = presentation.getShareLink();
        String link = shareLink != null ? shareLink.getLink() : null;
        val tags = presentation.getTags();
        return new PresentationDto(id, title, tags, slidesIds, link);
    }
}
