package com.kolev.stock.app.myapp.models.requests.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kolev.stock.app.myapp.models.requests.users.base.UserAuthRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginRequest extends UserAuthRequest {
}
