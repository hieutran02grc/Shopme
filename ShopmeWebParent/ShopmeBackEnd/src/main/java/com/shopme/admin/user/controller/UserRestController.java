package com.shopme.admin.user.controller;

import com.shopme.admin.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService service;

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(Integer id, String email) {
        return service.isEmailUnique(id, email) ? "OK" : "Duplicated";
    }
}
