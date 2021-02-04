package pl.dmcs.enums;

public enum BookCategory {
    FANTASY, DRAMAT, KOMEDIA, AUTOBIOGRAFIA;

    public static BookCategory getByOrdinal(int ordinal) {
        return values()[ordinal];
    }
}
