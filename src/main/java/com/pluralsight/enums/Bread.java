package com.pluralsight.enums;

public enum Bread {
    WHITE("White"),
    WHEAT("Wheat"),
    RYE("Rye"),
    WRAP("Wrap"),
    QUIT("Quit");

    private final String displayName;

    Bread(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
