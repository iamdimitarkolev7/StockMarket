package com.kolev.stock.app.myapp.models.requests.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthRequest {

    private String username;
    private String password;
}
