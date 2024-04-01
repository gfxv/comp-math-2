package dev.gfxv.utils;

import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;
import dev.gfxv.entities.Boundaries;
import dev.gfxv.samples.Equation;
import dev.gfxv.samples.SystemOfEquations;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plotter {

    Equation equation;
    Boundaries boundaries;

    final double step = 0.1;
    final double margin = 1;



    public void show() throws PythonExecutionException, IOException {

        double length = ((boundaries.getA() + boundaries.getB()) / step);

        List<Double> xs = new ArrayList<>();
        List<Double> ys = new ArrayList<>();

        for (double x = boundaries.getA() - margin; x < boundaries.getB() + margin; x += step) {
            double y = equation.f(x);
            xs.add(x);
            ys.add(y);
        }

        Plot plot = Plot.create();
        plot.plot().add(xs, ys);
        plot.title(equation.toString());
        plot.show();

    }

}



