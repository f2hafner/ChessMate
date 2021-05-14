package com.game.chessmate.GameFiles;

public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector vector) {
        return new Vector(this.x + vector.x, this.y + vector.y);
    }

    public Vector sub(Vector vector) {
        return new Vector(this.x - vector.x, this.y - vector.y);
    }

    public Vector div(int divisor) {
        return new Vector(this.x / divisor, this.y / divisor);
    }



    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
