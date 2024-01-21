package com.messenger.config;

////import com.auth.util.ByteArraySerializer;
////import com.auth.util.ByteArrayDeserializer;
import com.messenger.util.JacksonObjectMapper;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class BeansConfig {
    @Bean
    public JacksonObjectMapper customObjectMapper() {
        JacksonObjectMapper jsonMapper = new JacksonObjectMapper();
        SimpleModule module = new SimpleModule("ByteArraySerializer", new Version(1, 0, 0, ""));
//        module.addSerializer(byte[].class, new ByteArraySerializer());
//        module.addDeserializer(byte[].class, new ByteArrayDeserializer());
        jsonMapper.registerModule(module);
        return jsonMapper;
    }

    @Bean
    public AcceptHeaderLocaleResolver localeResolver() {
        final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages/app");
        return source;
    }

    @Bean
    public MessageSourceAccessor getMessageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }
}
