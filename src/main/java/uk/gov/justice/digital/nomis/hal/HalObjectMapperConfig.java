package uk.gov.justice.digital.nomis.hal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HalObjectMapperConfig {
    private static final String HAL_OBJECT_MAPPER_BEAN_NAME = "_halObjectMapper";

    private final BeanFactory beanFactory;

    @Autowired
    public HalObjectMapperConfig(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    // Annoying workaround, Spring HATEOAS creates its own ObjectMapper ffs
    @Bean
    public ObjectMapper halObjectMapper() {
        ObjectMapper mapper = (ObjectMapper) beanFactory.getBean(HAL_OBJECT_MAPPER_BEAN_NAME);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .registerModules(new Jdk8Module(), new JavaTimeModule());

        return mapper;
    }
}