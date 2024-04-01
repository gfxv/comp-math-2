package dev.gfxv;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.ConvergenceException;
import dev.gfxv.exceptions.InvalidInputException;
import dev.gfxv.exceptions.RootOutOfBoundaries;
import dev.gfxv.methods.*;
import dev.gfxv.samples.*;
import dev.gfxv.utils.Parser;

import java.util.Arrays;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws RootOutOfBoundaries, ConvergenceException {

        Scanner sc = new Scanner(System.in);

        Equation eq = new Equation1();
        Boundaries b = new Boundaries(2, 3, 0.001);
//        Solver halfDivision = new HalfDivision(eq, new Boundaries(2, 3, 0.001));
//        Solver newton = new Newton(eq, b);
//        System.out.println(newton.solve());

//        Solver simpleIter = new SimpleIteration(eq, b);
//        System.out.println(simpleIter.solve());

        SystemOfEquations soe1 = new SOE1();
        Boundaries boundaries = new Boundaries(1, 1, 0.01);

        SOESolver solver = new SOENewton(soe1, boundaries);
        System.out.println(Arrays.toString(solver.solve()));

//        Equation[] equations  = { new Equation1(), new Equation2(), new Equation3() };
//        SystemOfEquations[] systemOfEquations = { new SOE1(), new SOE2() };
//
//        Solver[] methods;
//
//        try {
//            System.out.println("Choose equation (system of equations)");
//
//            // PRINT ALL EQUATIONS HERE
//
//            int eqIndex = Parser.parseInt(sc.nextLine());
//            if (eqIndex < 0 || eqIndex > equations.length + systemOfEquations.length) {
//                throw new InvalidInputException("Input should be between 0 and " + equations.length + systemOfEquations.length);
//            }
//
//            int methodIndex = Parser.parseInt(sc.nextLine());
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }


    }

}
