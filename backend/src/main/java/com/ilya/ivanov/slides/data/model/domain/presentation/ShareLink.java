package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.ilya.ivanov.slides.data.model.domain.presentation.ShareLink.TABLE_KEY;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table(name = TABLE_KEY)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShareLink {
    public static final String TABLE_KEY = "share_link";

    public static final String ID_KEY = "id";
    public static final String LINK_KEY = "link";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_KEY)
    @Getter(onMethod = @__(@JsonIgnore))
    private Long id;

    @Column(name = LINK_KEY)
    private String link;

    public ShareLink merge(String shareLink) {
        this.link = shareLink;
        return this;
    }
}
