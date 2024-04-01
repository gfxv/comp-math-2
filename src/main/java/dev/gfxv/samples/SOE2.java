package dev.gfxv.samples;

public class SOE2 implements SystemOfEquations {
    @Override
    public double[] getSEO(double x, double y) {
        return new double[] {
                Math.sin(x) - 4 * y + 5,
                2 * Math.pow(x, 2) - Math.pow(y, 2)
        };
    }

    @Override
    public double[][] getJacobian(double x, double y) {
        return new double[][] {
                { Math.cos(x), -4},
                {4 * x, -2 * y}
        };
    }

    @Override
    public String toString() {
        return "{ sin(x) - 4y + 5 = 0\n{ 2x^2 - y^2 = 0";
    }
}
