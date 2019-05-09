package com.github.shewaeger.bookschecker.dto.user;

import com.github.vastik.spring.extensions.aop.Validate;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
@Validate
public class UserPasswordWrapper {
    @Size(min = 5, message = "Пароль не может быть короче 5-ти символов")
    private String password;
    private String confirm;
}
