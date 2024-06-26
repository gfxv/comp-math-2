package dev.gfxv.methods;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.ConvergenceException;
import dev.gfxv.exceptions.RootOutOfBoundaries;
import dev.gfxv.samples.Equation;
import lombok.Setter;

public interface Solver {

    double solve() throws RootOutOfBoundaries, ConvergenceException;

}
