package com.ilya.ivanov.slides.controller.api;

import com.ilya.ivanov.slides.service.AutocompleteService;
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

import java.util.Collection;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by i.ivanov on 12/9/17.
 */
@RestController
@RequestMapping(value = "/api/autocomplete")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "/api/autocomplete")
public class AutocompleteController {

    private final AutocompleteService autocompleteService;

    @PostMapping("/tag")
    @ApiOperation(response = String.class, responseContainer = "List", value = "Autocomplete results")
    @ApiResponse(code = SC_OK, message = "Successful search")
    public Collection<String> fullTextSearch(@ApiParam @RequestParam String request) {
        return autocompleteService.autocompleteTag(request);
    }
}
