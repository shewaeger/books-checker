package com.github.shewaeger.bookschecker.controllers;

import com.github.shewaeger.bookschecker.dto.user.UserWrapper;
import com.github.shewaeger.bookschecker.filters.SimpleFilter;
import com.github.shewaeger.bookschecker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService service;

    @GetMapping("{id}")
    public UserWrapper get(@PathVariable Long id){
        return service.getUser(id);
    }

    @PostMapping
    public UserWrapper create(UserWrapper wrapper){
        return service.create(wrapper);
    }

    @PatchMapping
    public UserWrapper edit(@RequestBody UserWrapper wrapper){
        return service.edit(wrapper);
    }

    @PostMapping("list")
    public List<UserWrapper> find(@RequestBody SimpleFilter filter){
        return service.find(filter);
    }

}
