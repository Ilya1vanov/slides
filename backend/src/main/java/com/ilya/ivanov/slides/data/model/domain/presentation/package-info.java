/**
 * Created by i.ivanov on 11/27/17.
 */

@AnalyzerDefs({
        @AnalyzerDef(name = namesAnalyzer,
                tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
                                @Parameter(name = "minGramSize", value = MIN_STRING_FIELD_LEN_STRING),
                                @Parameter(name = "maxGramSize", value = MAX_STRING_FIELD_LEN_STRING)
                        })
                }
        ),
        @AnalyzerDef(name = usernameAnalyzer,
                tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {
                                @Parameter(name = "minGramSize", value = MIN_STRING_FIELD_LEN_STRING),
                                @Parameter(name = "maxGramSize", value = MAX_STRING_FIELD_LEN_STRING)
                        })
                }
        ),
        @AnalyzerDef(name = fullTextAnalyzer,
                tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                                @Parameter(name = "language", value = "English")
                        }),
                        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                                @Parameter(name = "language", value = "Russian")
                        }),
                        @TokenFilterDef(factory = StopFilterFactory.class, params = {
                                @Parameter(name = "words", value = "classpath:/stoplist/stoplist.en.txt"),
                                @Parameter(name = "ignoreCase", value = "true")
                        })
                }
        ),
        @AnalyzerDef(name = tagsAnalyzer,
                tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
                charFilters = @CharFilterDef(factory = PatternReplaceCharFilterFactory.class, params = {
                        @Parameter(name = "pattern", value = "#"),
                        @Parameter(name = "replacement", value = "")
                }),
                filters = {
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class)
                }
        )
})
package com.ilya.ivanov.slides.data.model.domain.presentation;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceCharFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;

import static com.ilya.ivanov.slides.constants.SearchConstants.*;
import static com.ilya.ivanov.slides.constants.ValidationConstants.MAX_STRING_FIELD_LEN_STRING;
import static com.ilya.ivanov.slides.constants.ValidationConstants.MIN_STRING_FIELD_LEN_STRING;