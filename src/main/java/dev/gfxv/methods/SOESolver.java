package dev.gfxv.methods;

import dev.gfxv.exceptions.ConvergenceException;
import dev.gfxv.exceptions.RootOutOfBoundaries;

public interface SOESolver {

    double[] solve() throws RootOutOfBoundaries, ConvergenceException;
}
