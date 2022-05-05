package org.bci.app.domain.configuration;

import lombok.AllArgsConstructor;
import org.bci.app.domain.utils.TokenUtils;
import org.bci.app.infraestructure.repositories.jpa.IUserJPARepository;
import org.bci.app.usecases.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserUseCaseConfiguration {

    private final IUserJPARepository iUserJPARepository;
    private final TokenUtils tokenUtils;

    @Bean
    public UserUseCase instantiateUserUserCase(){
        return new UserUseCase(iUserJPARepository, tokenUtils);
    }

}
