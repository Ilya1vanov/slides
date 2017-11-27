package com.ilya.ivanov.slides.controller.api;

import com.ilya.ivanov.slides.data.model.dto.search.SearchResults;
import com.ilya.ivanov.slides.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by i.ivanov on 11/25/17.
 */
@RestController
@RequestMapping(value = "/api/search")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "/api/search")
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/")
    @ApiOperation(response = SearchResults.class, responseContainer = "List", value = "Search results returned")
    @ApiResponse(code = SC_OK, message = "Successful search")
    public SearchResults fullTextSearch(@ApiParam @RequestParam String request) {
        return searchService.fullTextSearch(request);
    }
}
