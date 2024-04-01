package dev.gfxv.samples;

public class SOE2 implements SystemOfEquations {
    @Override
    public double[] getSEO(double x, double y) {
        return new double[0];
    }

    @Override
    public double[] getJacobian(double x, double y) {
        return new double[0];
    }
}
