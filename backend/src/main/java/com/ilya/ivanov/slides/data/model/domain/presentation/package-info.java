/**
 * Created by i.ivanov on 11/27/17.
 */

@AnalyzerDef(name = defaultAnalyzerName,
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
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
        })
package com.ilya.ivanov.slides.data.model.domain.presentation;

import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import static com.ilya.ivanov.slides.constants.SearchConstants.defaultAnalyzerName;