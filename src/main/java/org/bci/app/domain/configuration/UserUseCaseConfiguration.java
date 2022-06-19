package org.bci.app.domain.configuration;

import org.bci.app.domain.utils.TokenUtils;
import org.bci.app.infraestructure.IUserRepository;
import org.bci.app.usecases.UserUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfiguration {

    private final IUserRepository iUserJPARepository;
    private final TokenUtils tokenUtils;

    public UserUseCaseConfiguration(
            @Qualifier("Google") IUserRepository iUserJPARepository,
            TokenUtils tokenUtils
    ) {
        this.iUserJPARepository = iUserJPARepository;
        this.tokenUtils = tokenUtils;
    }

    @Bean
    public UserUseCase userUseCase() {
        return new UserUseCase(iUserJPARepository, tokenUtils);
    }

}
