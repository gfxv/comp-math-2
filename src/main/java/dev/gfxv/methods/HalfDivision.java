package dev.gfxv.methods;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.RootOutOfBoundaries;
import dev.gfxv.samples.Equation;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HalfDivision  implements Solver {

    Equation equation;
    Boundaries boundaries;



    @Override
    public double solve() throws RootOutOfBoundaries {

        double x0 = (boundaries.getA() + boundaries.getB()) / 2;

        double f_a = equation.f(boundaries.getA());
        double f_b = equation.f(boundaries.getB());
        double f_x = equation.f(x0);

        if (Math.abs(boundaries.getA() - boundaries.getB()) < boundaries.getE()) return x0;

        if (f_a * f_x < 0) {
            boundaries.setB(x0);
            return solve();
        }

        if (f_b * f_x < 0) {
            boundaries.setA(x0);
            return solve();
        }

        throw new RootOutOfBoundaries(
                String.format("Root is out of boundaries [%f, %f]", boundaries.getA(), boundaries.getB())
        );
    }

    @Override
    public String toString() {
        return "Half Division method";
    }
}
