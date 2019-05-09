package com.github.shewaeger.bookschecker.services;

import com.github.shewaeger.bookschecker.configs.seccurity.SimpleUserDetails;
import com.github.shewaeger.bookschecker.dto.user.UserPasswordWrapper;
import com.github.shewaeger.bookschecker.dto.user.UserWrapper;
import com.github.shewaeger.bookschecker.entity.User;
import com.github.shewaeger.bookschecker.filters.SimpleFilter;
import com.github.shewaeger.bookschecker.repositories.UserRepository;
import com.github.shewaeger.bookschecker.repositories.specification.UserSpecification;
import com.github.vastik.spring.extensions.exception.ServiceException;
import com.github.vastik.spring.extensions.utility.Asserts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username);
        Asserts.notNull(user, "Bad credentials");
        return new SimpleUserDetails(user);
    }

    public UserWrapper getUser(Long id) {
        User user = repository.getOne(id);
        Asserts.notNull(user, "Unable to get user");
        return new UserWrapper(user);
    }

    public List<UserWrapper> find(SimpleFilter filter){
        return repository.findAll(new UserSpecification(filter))
                .stream().map(UserWrapper::new).collect(Collectors.toList());
    }

    public UserWrapper edit(UserWrapper wrapper){
        if(wrapper == null || wrapper.getId() == null)
            throw new ServiceException("Unable to edit unknown user");
        User user = repository.getOne(wrapper.getId());
        wrapper.fromWrapperSimple(user);

        //update password
        UserPasswordWrapper passwordWrapper = wrapper.getPasswordWrapper();
        if(passwordWrapper != null){
            if(!passwordWrapper.getPassword().equals(passwordWrapper.getConfirm()))
                throw new ServiceException("Confirm not equals to password");
            String password = new BCryptPasswordEncoder().encode(passwordWrapper.getPassword());
            user.setPassword(password);
        }

        return new UserWrapper(repository.save(user));
    }

    public UserWrapper create(UserWrapper wrapper){
        if(wrapper == null)
            throw new ServiceException("Unable to create user");

        if(wrapper.getPasswordWrapper() == null)
            throw new ServiceException("Empty password");

        User user = new User();
        wrapper.fromWrapperSimple(user);

        UserPasswordWrapper passwordWrapper = wrapper.getPasswordWrapper();

        if(!passwordWrapper.getPassword().equals(passwordWrapper.getConfirm()))
            throw new ServiceException("Confirm not equals to password");

        String password = new BCryptPasswordEncoder().encode(passwordWrapper.getPassword());
        user.setPassword(password);

        return new UserWrapper(repository.save(user));
    }

}
