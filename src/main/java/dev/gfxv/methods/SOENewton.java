package dev.gfxv.methods;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.ConvergenceException;
import dev.gfxv.exceptions.RootOutOfBoundaries;
import dev.gfxv.samples.SystemOfEquations;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;

@Data
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



}


class NonlinearEquationSolver {

    public static void main(String[] args) {
        double[] initialGuess = {1.0, 1.0}; // Initial guess for the solution
        double[] solution = solveSystemOfEquations(initialGuess);

        System.out.println("Solution: x = " + solution[0] + ", y = " + solution[1]);
    }

    public static double[] solveSystemOfEquations(double[] initialGuess) {
        double[] currentGuess = initialGuess;
        double epsilon = 1e-6; // Tolerance for convergence
        int maxIterations = 100;

        for (int i = 0; i < maxIterations; i++) {
            double[] f = calculateSystemOfEquations(currentGuess);
            double[][] jacobian = calculateJacobianMatrix(currentGuess);

            double[][] inverseJacobian = invertMatrix(jacobian);
            double[] delta = matrixVectorMultiply(inverseJacobian, f);

            currentGuess[0] -= delta[0];
            currentGuess[1] -= delta[1];

            if (Math.abs(delta[0]) < epsilon && Math.abs(delta[1]) < epsilon) {
                break;
            }
        }

        return currentGuess;
    }

    public static double[] calculateSystemOfEquations(double[] point) {
        double x = point[0];
        double y = point[1];
        return new double[]{x * x + y * y - 1, x * y - 1};
    }

    public static double[][] calculateJacobianMatrix(double[] point) {
        double x = point[0];
        double y = point[1];
        return new double[][]{{2 * x, 2 * y}, {y, x}};
    }

    public static double[][] invertMatrix(double[][] matrix) {
        double determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        double[][] inverseMatrix = {{matrix[1][1] / determinant, -matrix[0][1] / determinant},
                {-matrix[1][0] / determinant, matrix[0][0] / determinant}};
        return inverseMatrix;
    }

    public static double[] matrixVectorMultiply(double[][] matrix, double[] vector) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }
}


