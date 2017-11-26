package com.ilya.ivanov.slides.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by i.ivanov on 11/26/17.
 */
@Component
@Primary
@Getter
public class JwtConstants {

    @Getter
    @Setter(onMethod = @__(@Value("${security.jwt.scope-read}")))
    private static String scopeRead = "read";

    @Getter
    @Setter(onMethod = @__(@Value("${security.jwt.scope-write}")))
    private static String scopeWrite = "write";

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    @Value("${security.jwt.grant-type}")
    private String grantType;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Value("${security.jwt.user-id-key}")
    private String userIdKey;

}
