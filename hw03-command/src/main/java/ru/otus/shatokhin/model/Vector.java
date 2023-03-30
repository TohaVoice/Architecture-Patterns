package ru.otus.shatokhin.model;

public record Vector(int x, int y) {

    public static Vector plus(Vector position, Vector velocity) {
        return new Vector(
                position.x() + velocity.x(),
                position.y() + velocity.y()
        );
    }
}
