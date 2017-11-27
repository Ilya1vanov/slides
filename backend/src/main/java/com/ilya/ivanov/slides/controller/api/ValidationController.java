package com.ilya.ivanov.slides.controller.api;

import com.ilya.ivanov.slides.data.model.dto.validation.EmailDto;
import com.ilya.ivanov.slides.data.model.dto.validation.UsernameDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by i.ivanov on 11/26/17.
 */
@RestController
@RequestMapping("/api/validate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "/api/validate")
public class ValidationController {

    @PostMapping("/username")
    @ApiOperation(response = void.class, value = "Nothing returned when username is valid")
    @ApiResponse(code = SC_OK, message = "Username is valid")
    public void validateUsername(@ApiParam @RequestBody @Valid UsernameDto usernameDto) {
    }

    @PostMapping("/email")
    @ApiOperation(response = void.class, value = "Nothing returned when email is valid")
    @ApiResponse(code = SC_OK, message = "Email is valid")
    public void validateEmail(@ApiParam @RequestBody @Valid EmailDto emailDto) {
    }
}
