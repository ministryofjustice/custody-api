package uk.gov.justice.digital.nomis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@SpringBootApplication
@Slf4j
public class NewNomisAPI {

    public static void main(String[] args) {
        SpringApplication.run(NewNomisAPI.class, args);
    }

    @Bean(name = "globalObjectMapper")
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .registerModules(new Jdk8Module(), new JavaTimeModule());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(@Qualifier("globalObjectMapper") ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> buildInfoLogger() {
        return event -> {
            try {
                log.info("BUILD PROPERTIES:");
                BuildProperties buildProperties = (BuildProperties) event.getApplicationContext().getBean("buildProperties");
                buildProperties.iterator().forEachRemaining(prop -> log.info("{} : {}", prop.getKey(), prop.getValue()));
            } catch (NoSuchBeanDefinitionException nsbde) {
                log.warn("No build info found! Is this a local build?");
            }
        };
    }

}
