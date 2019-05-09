package com.github.shewaeger.bookschecker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.shewaeger.bookschecker.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserWrapper {

    private Long id;

    private String login;

    private Boolean active;

    @JsonIgnore
    private String password;

    public UserWrapper(User item){
        toWrapper(item);
    }

    private void toWrapper(User item){
        this.id = item.getId();
        this.login = item.getLogin();
        this.active = item.getActive();
        this.password = item.getPassword();
    }

}
