package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ElementColors {
    GREEN("61, 220, 145"),
    RED("226, 35, 26");

    private final String elementColor;
}
