package com.github.shewaeger.bookschecker.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shewaeger.bookschecker.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserWrapper {

    private Long id;

    private String login;

    private Boolean active;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserPasswordWrapper passwordWrapper;

    public UserWrapper(User item){
        toWrapper(item);
    }

    private void toWrapper(User item){
        this.id = item.getId();
        this.login = item.getLogin();
        this.active = item.getActive();
    }

    public void fromWrapperSimple(User item){
        item.setActive(this.active);
        item.setLogin(this.getLogin());
    }
}
