package dev.gfxv.samples;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SOE1 implements SystemOfEquations {

    @Override
    public double[] getSEO(double x, double y) {
        return new double[] {
            Math.pow(x, 2) + Math.pow(y, 2) - 4,
            -3 * Math.pow(x, 2) + y
        };
    }

    @Override
    public double[][] getJacobian(double x, double y) {
        return new double[][] {
                {2 * x,  2 * y},
                {-6 * x,  1}
        };
    }

    @Override
    public String toString() {
        return "{ x^2 + y^2 - 4 = 0\n{ -3x^2 + y = 0";
    }

}
