package dev.gfxv.samples;

public class Equation3 implements Equation{

    @Override
    public double f(double x) {
        return Math.sqrt(Math.pow(x, 3) + 4) - 3;
    }

    @Override
    public double df(double x) {
        return 3 * Math.pow(x, 2) / (2 * Math.sqrt(Math.pow(x, 3) + 4));
    }

    @Override
    public String toString() {
        return "sqrt(x^3 + 4) - 3 = 0";
    }
}
