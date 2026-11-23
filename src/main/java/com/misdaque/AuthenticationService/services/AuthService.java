package com.misdaque.AuthenticationService.services;

import com.misdaque.AuthenticationService.exceptions.UserAlreadyExistsException;
import com.misdaque.AuthenticationService.exceptions.UserNotFoundException;
import com.misdaque.AuthenticationService.exceptions.WrongPasswordException;
import com.misdaque.AuthenticationService.modals.User;
import com.misdaque.AuthenticationService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean signUp(String email, String password) throws UserAlreadyExistsException {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + email + " already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);

        return true;
    }

    public boolean login(String email, String password) throws UserNotFoundException, WrongPasswordException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }

        if (!userOptional.get().getPassword().equals(password)) {
            throw new WrongPasswordException("Wrong Password");
        }

        return true;
    }

}
