package com.ilya.ivanov.slides.controller.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by i.ivanov on 11/25/17.
 */
@RestController
@RequestMapping(value = "/api/search")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "/api/search")
public class SearchController {
}
