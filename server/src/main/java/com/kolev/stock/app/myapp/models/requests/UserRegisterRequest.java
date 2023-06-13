package com.kolev.stock.app.myapp.models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kolev.stock.app.myapp.models.requests.base.UserAuthRequest;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterRequest extends UserAuthRequest {

    private String firstName;
    private String lastName;
    private String confirmPassword;
}
