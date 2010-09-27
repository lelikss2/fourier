package ru.ssau.karanashev.fourier;

import ru.ssau.karanashev.complex.ComplexSample;

import static ru.ssau.karanashev.complex.ComplexOperations.*;

/**
 * Date: Sep 22, 2010
 * Time: 9:22:53 PM
 *
 * @author Mukhamed Karanashev
 */
public class FastFourierTransformer2 implements FourierTransformer {

    private static final double EPS = 1e-9;

    public ComplexSample transform(ComplexSample sample) {

        int size = sample.size();

        if ((size & (size - 1)) != 0) {
            throw new IllegalArgumentException("Sample size must be power of 2");
        }

        ComplexSample rhs = sample.binaryInverse();
        ComplexSample lhs = new ComplexSample(size);

        double[] x0 = rhs.get(0);
        double[] x1 = rhs.get(1);

        double coef = -(Math.PI);

        double[] exp_0 = exp(coef * 0, null);
        double[] exp_1 = exp(coef, null);

        lhs.set(0, add(x0, mult(exp_0, x1, null), null));
        lhs.set(1, add(x0, mult(exp_1, x1, null), null));

        return lhs;
    }

}
