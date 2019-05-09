package com.github.shewaeger.bookschecker.controllers;

import com.github.shewaeger.bookschecker.dto.UserWrapper;
import com.github.shewaeger.bookschecker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService service;

    @GetMapping("{id}")
    public UserWrapper get(@PathVariable Long id){
        return service.getUser(id);
    }



}
