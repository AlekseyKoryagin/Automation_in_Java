package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withStandardPermission() {
        return new User(PropertyReader.getProperty("automation_in_java.username"), PropertyReader.getProperty("automation_in_java.password"));
    }

    public static User withWrongPassword() {
        return new User(PropertyReader.getProperty("automation_in_java.username"), PropertyReader.getProperty("automation_in_java.wrongPassword"));
    }

    public static User withWrongUsername() {
        return new User(PropertyReader.getProperty("automation_in_java.wrongUsername"), PropertyReader.getProperty("automation_in_java.password"));
    }

    public static User withLockedOutUser() {
        return new User(PropertyReader.getProperty("automation_in_java.lockedUser"), PropertyReader.getProperty("automation_in_java.password"));
    }

    public static User withEmptyFields() {
        return new User(PropertyReader.getProperty("automation_in_java.emptyField"), PropertyReader.getProperty("automation_in_java.emptyField"));
    }

    public static User withEmptyUsernameField() {
        return new User(PropertyReader.getProperty("automation_in_java.emptyField"), PropertyReader.getProperty("automation_in_java.password"));
    }

    public static User withEmptyPasswordField() {
        return new User(PropertyReader.getProperty("automation_in_java.username"), PropertyReader.getProperty("automation_in_java.emptyField"));
    }

    public static User withUpperCaseUsername() {
        return new User(PropertyReader.getProperty("automation_in_java.upperCaseUsername"), PropertyReader.getProperty("automation_in_java.upperCaseUsername"));
    }
}
