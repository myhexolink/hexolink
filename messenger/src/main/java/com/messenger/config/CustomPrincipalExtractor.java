package com.messenger.config;

import com.messenger.entity.OAuthUser;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Map;

@Component
public class CustomPrincipalExtractor implements PrincipalExtractor {

    @Override
    public OAuthUser extractPrincipal(Map<String, Object> map) {
        Map principal = (Map) map.get("principal");
        Integer id = (Integer) principal.get("id");
        String email = (String) principal.get("email");
        String color = (String) principal.get("color");
        String firstName = (String) principal.get("first_name");
        String lastName = (String) principal.get("last_name");
        String bio = (String) principal.get("bio");
        LocalDate birthDate = null;
        if (principal.get("birth_date") != null) {
            birthDate = LocalDate.parse(principal.get("birth_date").toString());
        }
        String gender = (String) principal.get("gender");
        String avatar1 = (String) principal.get("avatar");
        return new OAuthUser(
                id,
                email,
                color,
                firstName,
                lastName,
                bio,
                birthDate,
                gender,
                Base64.getDecoder().decode(avatar1)
        );
    }
}
