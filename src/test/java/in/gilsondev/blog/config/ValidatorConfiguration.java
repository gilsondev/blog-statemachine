package in.gilsondev.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class ValidatorConfiguration {

    @Bean
    public Validator getValidator() {
        return new LocalValidatorFactoryBean();
    }
}
