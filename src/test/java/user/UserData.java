package user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserData {
    private String firstName;
    private String lastName;
    private String postalCode;

    @Override
    public String toString() {
        return "First Name: " + firstName + ", Last Name: " + lastName + ", Postal Code: " + postalCode;
    }
}
