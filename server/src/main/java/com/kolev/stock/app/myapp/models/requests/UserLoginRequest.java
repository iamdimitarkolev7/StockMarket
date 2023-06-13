package com.kolev.stock.app.myapp.models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kolev.stock.app.myapp.models.requests.base.UserAuthRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginRequest extends UserAuthRequest {
}
