package com.github.shewaeger.bookschecker.services;

import com.github.shewaeger.bookschecker.configs.seccurity.SimpleUserDetails;
import com.github.shewaeger.bookschecker.dto.UserWrapper;
import com.github.shewaeger.bookschecker.entity.User;
import com.github.shewaeger.bookschecker.repositories.UserRepository;
import com.github.vastik.spring.extensions.utility.Asserts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username);
        Asserts.notNull(user, "Bad credentials");
        return new SimpleUserDetails(new UserWrapper(user));
    }

    public UserWrapper getUser(Long id) {
        User user = repository.getOne(id);
        Asserts.notNull(user, "Unable to get user");
        return new UserWrapper(user);
    }
}
