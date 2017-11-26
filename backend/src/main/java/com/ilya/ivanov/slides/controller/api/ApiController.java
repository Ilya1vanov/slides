package com.ilya.ivanov.slides.controller.api;

import com.ilya.ivanov.slides.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by i.ivanov on 11/23/17.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PreAuthorize("hasAuthority('STANDARD_USER')")
@Api(value = "/api")
public class ApiController {

    private final UserService userService;

//    @GetMapping("sharelink")
//    public ShareLink getShareLink(@RequestParam("id") Long presentationId) {
//        val presentation = presentationRepository.getOne(presentationId);
//
//    }
}
