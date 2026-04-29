package user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "Username: " + username + ", Password: " + password;
    }
}
