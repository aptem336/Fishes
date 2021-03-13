package com.example.fishes;

public class Vector {

    public static Vector nullVector = new Vector(0d, 0d);
    public static Vector xVector = new Vector(1d, 0d);
    public static Vector yVector = new Vector(0d, 1d);
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector angleDefine(double len, double angle) {
        return new Vector(Math.cos(angle) * len, Math.sin(angle) * len);
    }

    public double angle() {
        return Math.atan(y / x);
    }

    public double len() {
        return Math.sqrt(squareLen());
    }

    public double squareLen() {
        return x * x + y * y;
    }

    public void normalize() {
        double len = len();
        x /= len;
        y /= len;
    }

    public void set(double newX, double newY) {
        x = newX;
        y = newY;
    }

    public void set(Vector set) {
        x = set.x;
        y = set.y;
    }

    public void setToZero() {
        x = 0;
        y = 0;
    }

    public void invert() {
        x = -x;
        y = -y;
    }

    public void plus(Vector v) {
        x += v.x;
        y += v.y;
    }

    public void minus(Vector v) {
        x -= v.x;
        y -= v.y;
    }

    public void plus(Vector v, double div) {
        x += v.x * div;
        y += v.y * div;
    }

    public void multiply(double value) {
        x *= value;
        y *= value;
    }

    public void minus(Vector v, double div) {
        x -= v.x * div;
        y -= v.y * div;
    }

    public void setInvert() {
        x = -x;
        y = -y;
    }

    public void setRotated(double cos, double sin, Vector point, Vector center) {
        x = center.x + (point.x - center.x) * cos + (point.y - center.y) * sin;
        y = center.x - (point.x - center.x) * sin + (point.y - center.y) * cos;
    }

    public void setRightN() {
        set(-y, x);
    }

    public void setLeftN() {
        set(y, -x);
    }

    public void setDiff(Vector v1, Vector v2) {
        x = v2.x - v1.x;
        y = v2.y - v1.y;
    }

    public void setSum(Vector v1, Vector v2) {
        x = v1.x + v2.x;
        y = v1.y + v2.y;
    }

    public void setProduct(Vector v1, double val) {
        x = v1.x * val;
        y = v1.y * val;
    }

    public Vector getCopy() {
        return new Vector(x, y);
    }

    public Vector getInvert() {
        return new Vector(-x, -y);
    }

    public Vector getNormalized() {
        double len = len();
        return new Vector(x / len, y / len);
    }

    public static Vector getProj(Vector a, Vector b) {
        return getProduct(b, dotProduct(a, b));
    }

    public Vector getRightN() {
        return new Vector(-y, x);
    }

    public Vector getLeftN() {
        return new Vector(y, -x);
    }

    public static Vector getDiff(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector getSum(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector getProduct(Vector v1, double val) {
        return new Vector(v1.x * val, v1.y * val);
    }

    public static double dotProduct(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static double crossProduct(Vector v1, Vector v2) {
        return (v2.x * v1.y - v2.y * v1.x);
    }

    public double[][] toLine() {
        return new double[][]{{0d, 0d}, {x * 300d, y * 300d}};
    }
}
