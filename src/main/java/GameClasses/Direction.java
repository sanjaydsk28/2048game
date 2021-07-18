package GameClasses;

public enum Direction {
    LEFT,
    RIGHT,
    DOWN,
    UP;

    public static Direction toEnumType(int direction) {
        return switch (direction) {
            case 0 -> Direction.LEFT;
            case 1 -> Direction.RIGHT;
            case 2 -> Direction.DOWN;
            case 3 -> Direction.UP;
            default -> null;
        };
    }
}
