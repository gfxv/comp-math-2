package dev.gfxv;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.ConvergenceException;
import dev.gfxv.exceptions.InvalidInputException;
import dev.gfxv.exceptions.RootOutOfBoundaries;
import dev.gfxv.methods.*;
import dev.gfxv.samples.*;
import dev.gfxv.utils.Asker;
import dev.gfxv.utils.Parser;
import dev.gfxv.utils.Plotter;
import dev.gfxv.utils.Printer;

import java.util.Arrays;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws RootOutOfBoundaries, ConvergenceException {

        Scanner sc = new Scanner(System.in);

        Equation[] equations  = { new Equation1(), new Equation2(), new Equation3() };
        SystemOfEquations[] systemOfEquations = { new SOE1(), new SOE2() };


        try {

            System.out.println("Choose equation (system of equations)");

            Printer.printEquations(equations, systemOfEquations);

            System.out.printf("Type a number from 1 to %d to choose: ", equations.length + systemOfEquations.length);

            int eqIndex = Parser.parseInt(sc.nextLine());
            if (eqIndex < 0 || eqIndex > equations.length + systemOfEquations.length) {
                throw new InvalidInputException("Input should be between 1 and " + (equations.length + systemOfEquations.length));
            }

            // If we need to solve equation (not system)
            if (eqIndex <= equations.length) {

                System.out.println("Input boundaries");
                Boundaries boundaries = Asker.boundariesAsker(sc);

                Equation equation = equations[eqIndex - 1];
                Solver[] methods = {
                        new HalfDivision(equation, boundaries),
                        new Newton(equation, boundaries),
                        new SimpleIteration(equation, boundaries)
                };

                System.out.println("Choose method for solving your equation");
                Printer.printMethods(methods);

                System.out.printf("Type a number from 1 to %d to choose: ", equations.length);
                int methodIndex = Parser.parseInt(sc.nextLine());
                if (methodIndex < 0 || methodIndex > methods.length) {
                    throw new InvalidInputException("Input should be between 1 and " + methods.length);
                }

                Solver solver = methods[methodIndex - 1];

                System.out.printf("Solving:\n%s\nWith %s", equation, solver);

                System.out.println();
                System.out.println("Result: " + solver.solve());

//                Plotter plotter = new Plotter(equation, boundaries);
//                plotter.show();

                return;
            }

            eqIndex = eqIndex % (equations.length + 1);
            SystemOfEquations soe = systemOfEquations[eqIndex];
            System.out.println("Input initial approximation:");
            Boundaries boundaries = Asker.boundariesAsker(sc);

            SOESolver solver = new SOENewton(soe, boundaries);

            System.out.printf("Solving:\n%s\nWith %s", soe, solver);

            System.out.println();
            System.out.println("Result: " + Arrays.toString(solver.solve()));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
