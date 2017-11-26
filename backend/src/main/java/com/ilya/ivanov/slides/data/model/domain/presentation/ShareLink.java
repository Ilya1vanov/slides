package com.ilya.ivanov.slides.data.model.domain.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by i.ivanov on 11/23/17.
 */
@Entity
@Table(name = "share_link")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShareLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter(onMethod = @__(@JsonIgnore))
    private Long id;

    @Column(name = "link")
    private String link;

    public ShareLink merge(String shareLink) {
        this.link = shareLink;
        return this;
    }
}
