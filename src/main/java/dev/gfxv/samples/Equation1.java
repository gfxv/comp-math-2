package dev.gfxv.samples;

public class Equation1 implements Equation {

    @Override
    public double f(double x) {
        return Math.pow(x, 3) + 2 * Math.pow(x, 2) - 4 * x - 12;
    }

    @Override
    public double df(double x) {
        return 3 * Math.pow(x, 2) + 4 * x - 4;
    }

    @Override
    public String toString() {
        return "x^3 + 2x^2 - 4x - 12 = 0";
    }
}
