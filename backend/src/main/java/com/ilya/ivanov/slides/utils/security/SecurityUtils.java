package com.ilya.ivanov.slides.utils.security;

import com.ilya.ivanov.slides.constants.JwtConstants;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

/**
 * Created by i.ivanov on 11/25/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityUtils {

    private final TokenStore tokenStore;

    private final JwtConstants jwtConstants;

    public Long getUserId() {
        val details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        val accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return Long.parseLong(accessToken.getAdditionalInformation().get(jwtConstants.getUserIdKey()).toString());
    }
}
