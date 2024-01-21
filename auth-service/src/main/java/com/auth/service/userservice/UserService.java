package com.auth.service.userservice;

import com.auth.model.User;
import com.auth.repository.userrepository.UserRepository;
import com.auth.to.UserTo;
import com.auth.util.exception.IllegalRequestDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.auth.util.ValidationUtil.checkNotFoundWithId;
import static com.auth.util.converter.UserConverter.updateFromTo;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User getById(Integer id) {
        return checkNotFoundWithId(userRepository.findById(id).orElse(null), id);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public User create(User newFromTo) {
        return userRepository.save(newFromTo);
    }

    public void update(UserTo userTo, Integer userId) {
        if (!userTo.getId().equals(userId)) {
            throw new IllegalRequestDataException(userTo + " must be with id=" + userId);
        }
        User userByEmail = userRepository.getByEmail(userTo.getEmail());
        if (!userByEmail.getId().equals(userId)) {
            throw new IllegalRequestDataException(userTo + " must be with id=" + userId);
        }
        User user = checkNotFoundWithId(userRepository.getById(userTo.getId()), userTo.getId());
        userRepository.save(updateFromTo(user, userTo));
    }
}
