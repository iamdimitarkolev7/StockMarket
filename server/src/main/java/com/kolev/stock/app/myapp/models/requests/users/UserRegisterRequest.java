package com.kolev.stock.app.myapp.models.requests.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kolev.stock.app.myapp.models.requests.users.base.UserAuthRequest;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterRequest extends UserAuthRequest {

    private String firstName;
    private String lastName;
    private String confirmPassword;
}
