package org.bci.app.domain.configuration;

import lombok.AllArgsConstructor;
import org.bci.app.domain.utils.TokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@AllArgsConstructor
public class TokenUtilConfiguration {

    private final Environment environment;

    @Bean
    public TokenUtils instantiateTokenUtils(){
        return new TokenUtils(environment);
    }
}
