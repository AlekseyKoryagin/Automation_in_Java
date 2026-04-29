package user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserData {
    private String firstName;
    private String lastName;
    private String postalCode;
}
