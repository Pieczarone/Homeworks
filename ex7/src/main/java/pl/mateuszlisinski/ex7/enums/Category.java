package pl.mateuszlisinski.ex7.enums;

import lombok.ToString;

@ToString
public enum Category {
    PRACA("Praca"),
    DOM("Dom"),
    OGROD("Ogród");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
