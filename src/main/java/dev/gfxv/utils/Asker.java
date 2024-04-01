package dev.gfxv.utils;

import dev.gfxv.entities.Boundaries;
import dev.gfxv.exceptions.InvalidInputException;

import java.util.Scanner;

public class Asker {

    public static Boundaries boundariesAsker(Scanner sc) throws InvalidInputException {

        System.out.print("A: ");
        double a = Parser.parseDouble(sc.nextLine());
        System.out.print("B: ");
        double b = Parser.parseDouble(sc.nextLine());
        System.out.print("Epsilon: ");
        double e = Parser.parseDouble(sc.nextLine());

        return new Boundaries(a, b, e);
    }

}
