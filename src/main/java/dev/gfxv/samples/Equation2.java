package dev.gfxv.samples;

public class Equation2 implements Equation{

    @Override
    public double f(double x) {
        return 3 * Math.sin(x - 1) + 2;
    }

    @Override
    public double df(double x) {
        return 3 * Math.cos(x-1);
    }

    @Override
    public String toString() {
        return "3sin(x-1) + 2 = 0";
    }
}
