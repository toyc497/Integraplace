package com.api.integraplace.service;

import com.api.integraplace.entity.UserEntity;
import com.api.integraplace.form.LoginForm;
import com.api.integraplace.form.UserForm;
import com.api.integraplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository _UserRepository;

    public UserEntity save(UserForm userForm) {

        Optional<UserEntity> userAux = Optional.ofNullable(this._UserRepository.findByEmail(userForm.getEmail()));
        UserEntity userDB = null;

        if(userAux.isPresent()){
            userDB = userAux.get();
        }

        if (userDB != null){
            return null;
        }

        UserEntity user = new UserEntity();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(userForm.getPassword());
        user.setPassword(encryptedPassword);

        return this._UserRepository.save(user);

    }

    public void deleteUser(String emailAux) {

        Optional<UserEntity> userAux = Optional.ofNullable(this._UserRepository.findByEmail(emailAux));
        UserEntity user = null;

        if(userAux.isPresent()){
            user = userAux.get();
        }

        if (user != null){

            this._UserRepository.deleteById(user.getId());

        }

    }

    public LoginForm login(UserForm user) {

        Optional<UserEntity> userAux = Optional.ofNullable(this._UserRepository.findByEmail(user.getEmail()));
        UserEntity userDB = null;

        if(userAux.isPresent()){
            userDB = userAux.get();
        }

        LoginForm loginForm = new LoginForm();
        loginForm.setStatus("Unauthorized");

        if (userDB == null){
            return loginForm;
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(user.getPassword(), userDB.getPassword())){
            loginForm.setStatus("Authorized");
            return loginForm;
        }

        return loginForm;
    }
}
