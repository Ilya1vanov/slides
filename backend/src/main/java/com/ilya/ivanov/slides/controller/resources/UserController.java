package com.ilya.ivanov.slides.controller.resources;

import com.ilya.ivanov.slides.data.model.domain.user.User;
import com.ilya.ivanov.slides.data.repository.PresentationRepository;
import com.ilya.ivanov.slides.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@RestController
@RequestMapping("/resources/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PreAuthorize("hasAuthority('STANDARD_USER')")
@Api(value = "/resources/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    @PreAuthorize("#oauth2.hasScope(T(com.ilya.ivanov.slides.constants.JwtConstants).scopeRead)")
    @ApiOperation(response = User.class, value = "Return user repr")
    @ApiResponse(code = SC_OK, message = "User returned")
    public User getUser(Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}
