package com.api.integraplace.controller;

import com.api.integraplace.entity.UserEntity;
import com.api.integraplace.form.LoginForm;
import com.api.integraplace.form.UserForm;
import com.api.integraplace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService _UserService;

    @PostMapping("/save")
    public ResponseEntity<UserEntity> saveUser(@RequestBody @Valid UserForm userForm){

        UserEntity userAux = this._UserService.save(userForm);

        if (userAux == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userAux);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginForm> login(@RequestBody UserForm user){

        return ResponseEntity.status(HttpStatus.OK).body(this._UserService.login(user));

    }

    @DeleteMapping("/delete/{email}")
    public void deleteUser(@PathVariable("email") String emailAux){

        this._UserService.deleteUser(emailAux);

    }

}
