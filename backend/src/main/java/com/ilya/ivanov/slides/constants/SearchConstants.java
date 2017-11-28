package com.ilya.ivanov.slides.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by i.ivanov on 11/27/17.
 */
public final class SearchConstants {
    public static final String namesAnalyzer = "names-analyzer";
    public static final String usernameAnalyzer = "username-analyzer";
    public static final String fullTextAnalyzer = "fulltext-analyzer";
    public static final String tagsAnalyzer = "tags-analyzer";
    public static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static final String dateRangeSeparator = " ?(?: |-) ?";
}
