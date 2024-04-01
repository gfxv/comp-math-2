package dev.gfxv.utils;

import dev.gfxv.methods.SOESolver;
import dev.gfxv.methods.Solver;
import dev.gfxv.samples.Equation;
import dev.gfxv.samples.SystemOfEquations;

public class Printer {

    public static void printEquations(Equation[] equations, SystemOfEquations[] soes) {
        int counter = 1;
        for (Equation eq : equations) {
            System.out.printf("(%d) %s\n", counter, eq);
            counter++;
        }

        for (SystemOfEquations soe : soes) {
            System.out.printf("(%d) System: \n%s\n", counter, soe);
            counter++;
        }
    }

    public static void printMethods(Solver[] methods) {
        int counter = 1;
        for (Solver m : methods) {
            System.out.printf("(%d) %s\n", counter, m);
            counter++;
        }
    }
}
