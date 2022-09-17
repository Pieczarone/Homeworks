package com.isa.bookcase.model;

public enum Category {
    KRYMINAL_SENSACJA_THRILLER("kryminał, sensacja, thriller"),
    LITERATURA_PIEKNA("literatura piękna"),
    HISTORYCZNA("historyczna"),
    FANTASTYKA_SCIENCE_FICTION("fantastyka, science-fiction"),
    PUBLICYSTYKA_BIOGRAFIA("publicystyka, biografia"),
    OBYCZAJOWA_ROMANS("obyczajowa, romans");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
