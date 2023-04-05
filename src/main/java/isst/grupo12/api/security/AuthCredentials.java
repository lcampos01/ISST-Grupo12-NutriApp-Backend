package isst.grupo12.api.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
