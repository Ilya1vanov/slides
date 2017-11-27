package com.ilya.ivanov.slides.controller.resources;

import com.ilya.ivanov.slides.data.model.domain.presentation.Presentation;
import com.ilya.ivanov.slides.data.model.dto.presentation.PresentationDto;
import com.ilya.ivanov.slides.service.PresentationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

import static java.util.stream.Collectors.toList;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * Created by i.ivanov on 11/24/17.
 */
@RestController
@RequestMapping(value = "/resources/presentation")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@PreAuthorize("hasAuthority('STANDARD_USER')")
@Api(value = "/resources/presentation")
public class PresentationController {
    private final PresentationService presentationService;

    @Value("${security.jwt.scope-read}")
    private String scopeRead;

    @GetMapping("/all")
    @PreAuthorize("#oauth2.hasScope(T(com.ilya.ivanov.slides.constants.JwtConstants).scopeRead)")
    @ApiOperation(response = PresentationDto.class, responseContainer="List", value = "Return All user presentations repr")
    @ApiResponse(code = SC_OK, message = "Presentation returned")
    public Collection<PresentationDto> getPresentations() {
        return presentationService.getAllPresentations().stream()
                .map(Presentation::toDto)
                .collect(toList());
    }

    @PostMapping("/")
    @PreAuthorize("#oauth2.hasScope(T(com.ilya.ivanov.slides.constants.JwtConstants).scopeWrite)")
    @ApiOperation(response = PresentationDto.class, value = "Return Presentation repr")
    @ApiResponse(code = SC_OK, message = "Presentation created")
    public PresentationDto createPresentation(@ApiParam @RequestBody @Valid PresentationDto presentationDto) {
        return presentationService.addPresentation(presentationDto).toDto();
    }

    @PatchMapping("/")
    @PreAuthorize("#oauth2.hasScope(T(com.ilya.ivanov.slides.constants.JwtConstants).scopeWrite)")
    @ApiOperation(response = PresentationDto.class, responseContainer="List", value = "Update presentation")
    @ApiResponse(code = SC_OK, message = "Presentation updated")
    public PresentationDto editPresentation(@ApiParam @RequestBody @Valid PresentationDto presentationDto) {
        return presentationService.editPresentation(presentationDto).toDto();
    }

    @DeleteMapping("/")
    @PreAuthorize("#oauth2.hasScope(T(com.ilya.ivanov.slides.constants.JwtConstants).scopeWrite)")
    @ApiOperation(response = PresentationDto.class, responseContainer="List", value = "Remove presentation")
    @ApiResponse(code = SC_OK, message = "Presentation removed")
    public void deletePresentation(@ApiParam @RequestBody @Valid PresentationDto presentationDto) {
        presentationService.removePresentation(presentationDto);
    }
}
