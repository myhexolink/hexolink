package com.auth.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    private final ResourceLoader resourceLoader;
    private final String serviceAccountPath;

    public FirebaseConfig(ResourceLoader resourceLoader, @Value("${firebase.service-account-path}") String serviceAccountPath) {
        this.resourceLoader = resourceLoader;
        this.serviceAccountPath = serviceAccountPath;
    }

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        InputStream serviceAccountStream = getResourceStream(serviceAccountPath);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    @DependsOn("initializeFirebaseApp")
    public FirebaseAuth createFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    private InputStream getResourceStream(String path) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + path);
        return resource.getInputStream();
    }
}
