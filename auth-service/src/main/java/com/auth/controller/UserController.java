package com.auth.controller;

import com.auth.model.User;
import com.auth.service.userservice.UserService;
import com.auth.to.UserTo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-profile")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Principal getServiceUser(Principal principal) {
        return principal;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @Valid @RequestBody UserTo userTo,
            @AuthenticationPrincipal User user
    ) {
        userService.update(userTo, user.getId());
    }

//    @GetMapping("/avatar")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void getAvatar(
//            @AuthenticationPrincipal User user,
//            HttpServletResponse response
//    ) {
//        Avatar avatar = userService.getById(user.getId()).getAvatar();
//        response.setContentType("image/png");
//        response.setHeader("Accept-Ranges", "bytes");
//        try (OutputStream os = response.getOutputStream()) {
//            os.write(avatar.getAvatar());
//            os.flush();
//        } catch (Exception ignored) {
//        }
//    }
}
