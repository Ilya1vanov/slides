package com.ilya.ivanov.slides.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by i.ivanov on 11/27/17.
 */
public final class SearchConstants {
    public static final String NAMES_ANALYZER = "names-analyzer";
    public static final String USERNAME_ANALYZER = "username-analyzer";
    public static final String FULL_TEXT_ANALYZER = "fulltext-analyzer";
    public static final String TAGS_ANALYZER = "searchTags-analyzer";
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final String DATE_RANGE_SEPARATOR = " *(?: |-) *";

    public static final int AUTOCOMPLETE_PAGE_SIZE = 20;
}
