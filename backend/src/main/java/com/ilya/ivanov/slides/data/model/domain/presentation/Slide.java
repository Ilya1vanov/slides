package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.ilya.ivanov.slides.data.model.dto.presentation.SlideDto;
import lombok.*;

import javax.persistence.*;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table(name = "slide")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class Slide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    @NonNull
    private String content;

    public static Slide fromDto(SlideDto slideDto) {
        val content = slideDto.getContent();
        return new Slide(content);
    }

    public Slide merge(SlideDto slideDto) {
        return this;
    }
}
