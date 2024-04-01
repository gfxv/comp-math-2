package dev.gfxv.methods;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.RootOutOfBoundaries;
import dev.gfxv.samples.Equation;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Newton implements Solver {

    Equation equation;
    Boundaries boundaries;
    double x;

    public Newton(Equation equation, Boundaries boundaries) {
        this.equation = equation;
        this.boundaries = boundaries;

        this.x = (boundaries.getA() + boundaries.getB()) / 2;
    }

    @Override
    public double solve() throws RootOutOfBoundaries {

        double newX = x - (equation.f(x)) / (equation.df(x));

        if (Math.abs(newX - x) <= boundaries.getE())
            return newX;

        x = newX;
        return solve();
    }

    @Override
    public String toString() {
        return "Newton's method";
    }
}
