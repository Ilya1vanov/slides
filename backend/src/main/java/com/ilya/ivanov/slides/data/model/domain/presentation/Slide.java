package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.ilya.ivanov.slides.data.model.dto.presentation.SlideDto;
import lombok.*;

import javax.persistence.*;

import static com.ilya.ivanov.slides.data.model.domain.presentation.Slide.TABLE_KEY;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table(name = TABLE_KEY)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public final class Slide {
    public static final String TABLE_KEY = "slide";

    public static final String ID_KEY = "id";
    public static final String CONTENT_KEY = "content";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_KEY)
    private Long id;

    @Column(name = CONTENT_KEY)
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
