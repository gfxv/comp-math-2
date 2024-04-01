package dev.gfxv.methods;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.ConvergenceException;
import dev.gfxv.exceptions.RootOutOfBoundaries;
import dev.gfxv.samples.SystemOfEquations;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SOENewton implements SOESolver {

    SystemOfEquations soe;
    Boundaries boundaries;

    @Override
    public double[] solve() throws RootOutOfBoundaries, ConvergenceException {


        double x0 = 0;
        double y0 = 0;

        double newX = boundaries.getA();
        double newY = boundaries.getA();

        while (Math.abs(x0 - newX) > boundaries.getE() && Math.abs(y0 - newY) > boundaries.getE()) {

            x0 = newX;
            y0 = newY;

            double[] deltaSolution = solveMatrix(x0, y0);

            double deltaX = deltaSolution[0];
            double deltaY = deltaSolution[1];

            newX = x0 + deltaX;
            newY = y0 + deltaY;

        }

        return new double[]{ newX, newY };
    }

    private double[] solveMatrix(double x, double y) {

        double[][] coefficients = soe.getJacobian(x, y);
        double[] constants = Arrays.stream(soe.getSEO(x, y)).map(line -> -line).toArray();

        RealMatrix coefficientsMatrix = new Array2DRowRealMatrix(coefficients);
        RealMatrix constantVector = new Array2DRowRealMatrix(constants);

        DecompositionSolver solver = new LUDecomposition(coefficientsMatrix).getSolver();

        RealMatrix solutionVector = solver.solve(constantVector);

        return solutionVector.getColumn(0);
    }

    @Override
    public String toString() {
        return "Newton's method for system of equations";
    }

}
