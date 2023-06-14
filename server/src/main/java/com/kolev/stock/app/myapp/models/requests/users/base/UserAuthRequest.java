package com.kolev.stock.app.myapp.models.requests.users.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.server.ServletServerHttpRequest;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthRequest {

    private String username;
    private String password;
}
