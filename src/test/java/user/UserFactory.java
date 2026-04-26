package user;

import utils.PropertyReader;

public class UserFactory {
    public static String setProperty(String property) {
        return PropertyReader.getProperty(property);
    }

    public static User withStandardPermission() {
        return new User(setProperty("automation_in_java.username"), setProperty("automation_in_java.password"));
    }

    public static User withWrongPassword() {
        return new User(setProperty("automation_in_java.username"), setProperty("automation_in_java.wrongPassword"));
    }

    public static User withWrongUsername() {
        return new User(setProperty("automation_in_java.wrongUsername"), setProperty("automation_in_java.password"));
    }

    public static User withLockedOutUser() {
        return new User(setProperty("automation_in_java.lockedUser"), setProperty("automation_in_java.password"));
    }

    public static User withEmptyFields() {
        return new User(setProperty("automation_in_java.emptyField"), setProperty("automation_in_java.emptyField"));
    }

    public static User withEmptyUsernameField() {
        return new User(setProperty("automation_in_java.emptyField"), setProperty("automation_in_java.password"));
    }

    public static User withEmptyPasswordField() {
        return new User(setProperty("automation_in_java.username"), setProperty("automation_in_java.emptyField"));
    }

    public static User withUpperCaseUsername() {
        return new User(setProperty("automation_in_java.upperCaseUsername"), setProperty("automation_in_java.upperCaseUsername"));
    }

    public static UserData infoWithCorrectData() {
        return new UserData(setProperty("automation_in_java.firstName"), setProperty("automation_in_java.lastName"), setProperty("automation_in_java.postalCode"));
    }

    public static UserData infoWithEmptyFields() {
        return new UserData(setProperty("automation_in_java.emptyField"), setProperty("automation_in_java.emptyField"), setProperty("automation_in_java.emptyField"));
    }

    public static UserData infoWithEmptyFirstName() {
        return new UserData(setProperty("automation_in_java.emptyField"), setProperty("automation_in_java.lastName"), setProperty("automation_in_java.postalCode"));
    }

    public static UserData infoWithEmptyLastName() {
        return new UserData(setProperty("automation_in_java.firstName"), setProperty("automation_in_java.emptyField"), setProperty("automation_in_java.postalCode"));
    }

    public static UserData infoWithEmptyPostalCode() {
        return new UserData(setProperty("automation_in_java.firstName"), setProperty("automation_in_java.lastName"), setProperty("automation_in_java.emptyField"));
    }
}
