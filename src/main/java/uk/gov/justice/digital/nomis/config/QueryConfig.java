package uk.gov.justice.digital.nomis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

@Configuration
public class QueryConfig {

    @Bean
    BeanPostProcessor pageablePostProcessor(@Value("${maxPageSize:#{T(Integer).MAX_VALUE}}") Integer maxPageSize) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof HateoasPageableHandlerMethodArgumentResolver) {
                    ((HateoasPageableHandlerMethodArgumentResolver) bean).setMaxPageSize(maxPageSize);
                }

                return bean;
            }
        };
    }

}
