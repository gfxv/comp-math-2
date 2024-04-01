package dev.gfxv.methods;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.ConvergenceException;
import dev.gfxv.exceptions.RootOutOfBoundaries;
import dev.gfxv.samples.Equation;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleIteration implements Solver{

    Equation equation;
    Boundaries boundaries;

    @Override
    public double solve() throws RootOutOfBoundaries, ConvergenceException {

        double lambda = calculateLambda();

        if (dphi(boundaries.getA(), lambda) >= 1 || dphi(boundaries.getB(), lambda) >= 1) {
            throw new ConvergenceException("convergence condition is not satisfied");
        }

        double x = (boundaries.getA() + boundaries.getB()) / 3;
        double xNext = phi(x, lambda);

        while (Math.abs(xNext - x) > boundaries.getE()) {

            x = xNext;
            xNext = phi(x, lambda);
        }

        return xNext;
    }

    private double calculateLambda() {
        return -1 / Math.max(Math.abs(equation.df(boundaries.getA())), Math.abs(equation.df(boundaries.getB())));
    }

    private double phi(double x, double l) {
        return x + l * equation.f(x);
    }

    private double dphi(double x, double l) {
        return 1 + l * equation.df(x);
    }


    @Override
    public String toString() {
        return "Simple iteration method";
    }
}
