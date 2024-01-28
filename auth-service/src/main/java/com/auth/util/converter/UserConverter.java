package com.auth.util.converter;

import com.auth.model.Role;
import com.auth.model.User;
import com.auth.to.GoogleUserTo;
import com.auth.to.UserTo;

import java.time.LocalDateTime;
import java.util.Set;

public class UserConverter {

    public static User createNewFromTo(GoogleUserTo googleUserTo, Set<Role> role) {
        String[] firstLastName = googleUserTo.getName().split(" ");
        LocalDateTime updatedRegistered = LocalDateTime.now();
        return new User(
                null,
                firstLastName[0],
                firstLastName[1],
                googleUserTo.getEmail(),
                updatedRegistered,
                updatedRegistered,
                role,
                null
        );
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setFirstName(userTo.getFirstName());
        user.setLastName(userTo.getLastName());
        user.setEmail(userTo.getEmail());
        user.setRegistered(user.getRegistered());
        user.setUpdated(LocalDateTime.now());
        user.setColor(userTo.getColor());
        user.setRoles(user.getRoles());
        user.setAvatar(userTo.getAvatar());
        return user;
    }
}

