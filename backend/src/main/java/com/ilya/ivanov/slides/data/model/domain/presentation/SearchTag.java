package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import static com.ilya.ivanov.slides.constants.SearchConstants.TAGS_ANALYZER;
import static com.ilya.ivanov.slides.data.model.domain.presentation.SearchTag.TABLE_KEY;


/**
 * Created by i.ivanov on 12/9/17.
 */
@Entity
@Table(name = TABLE_KEY)
@Indexed
@Data
@EqualsAndHashCode(of = "tag")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchTag {
    public static final String TABLE_KEY = "searchTags";

    public static final String ID_KEY = "id";
    public static final String TAG_KEY = "tag";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_KEY)
    @Getter(onMethod = @__(@JsonIgnore))
    private Long id;

    @Column(name = TAG_KEY, unique = true, nullable = false)
    @Field
    @Analyzer(definition = TAGS_ANALYZER)
    private String tag;

    private SearchTag(String tag) {
        this.tag = tag;
    }

    public String toDto() {
        return tag;
    }

    @NotNull
    public static SearchTag fromDto(String tagDto) {
        return new SearchTag(tagDto);
    }
}
