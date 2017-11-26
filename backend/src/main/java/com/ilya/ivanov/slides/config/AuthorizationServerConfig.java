package com.ilya.ivanov.slides.config;

import com.google.common.collect.ImmutableMap;
import com.ilya.ivanov.slides.constants.JwtConstants;
import com.ilya.ivanov.slides.data.model.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * Created by ilya-laptop on 06/05/17.
 */
@Configuration
@EnableAuthorizationServer
@EnableResourceServer
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final JwtConstants jwtConstants;

	private final TokenStore tokenStore;

	private final JwtAccessTokenConverter accessTokenConverter;

	private final AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer
		        .inMemory()
		        .withClient(jwtConstants.getClientId())
		        .secret(jwtConstants.getClientSecret())
		        .authorizedGrantTypes(jwtConstants.getGrantType())
		        .scopes(JwtConstants.getScopeRead(), JwtConstants.getScopeWrite())
		        .resourceIds(jwtConstants.getResourceIds());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    val tokenEnhancerChain = new TokenEnhancerChain();
	    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(idEnhancer(), accessTokenConverter));
		endpoints.tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
		        .authenticationManager(authenticationManager);
	}

	@Bean
    public TokenEnhancer idEnhancer() {
	    return (oAuth2AccessToken, oAuth2Authentication) -> {
            val user = (User) oAuth2Authentication.getUserAuthentication().getPrincipal();
            val additionalInfo = ImmutableMap.of(jwtConstants.getUserIdKey(), (Object)user.getId());
            ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(additionalInfo);
            return oAuth2AccessToken;
        };
    }

}
