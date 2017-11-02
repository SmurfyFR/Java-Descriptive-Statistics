/**
 * MathWrapper is a class which was developed during the first project.
 */
public class MathWrapper {
    protected static boolean useJavaMath = false;

    /**
     * Permits to use Java's Math library (true) or our own implementation (false).
     * If false, MathWrapper's methods will only use basic arithmetic functions.
     * @param use Use Java's Math library (true) or our own implementation (false).
     */
    public static void useJavaMath(boolean use) {
        MathWrapper.useJavaMath = use;
    }

    /**
     * PI is defined here (same as Math.PI)
     * EPSILON is to fix the precision of our approximations
     */
    public static final double PI = 3.14159265358979323846;
    public static final double EPSILON = 0.000001;

    /**
     * Returns calculation of base^(exponent).
     * If useJavaMath is set to false, it will use recursivity (2^3 = 2 * 2^2 = 2 * 2 * 2 * 1)
     * @param base Base
     * @param exponent Exponent
     * @return base^(exponent)
     */
    public static double pow(double base, double exponent) {
        if(MathWrapper.useJavaMath) {
            return Math.pow(base, exponent);
        } else {
            if(exponent == 0.0) return 1.0;
            return base * pow(base, exponent - 1);
        }
    }

    /**
     * Calculates sqrt of a
     * @param a Base number
     * @return sqrt(a)
     */
    public static double sqrt(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.sqrt(a);
        } else {
            if(a == 0.0) {
                return 0;
            } else if(a < 0.0) {
                a = -a;
            }

            double sqrt = a / 2;
            double tmp = sqrt;
            do {
                tmp = sqrt;
                sqrt = (tmp + (a/tmp)) / 2;
            } while(tmp - sqrt > MathWrapper.EPSILON);

            return sqrt;
        }
    }

    /**
     * Calculates cubic root of a
     * @param a Base
     * @return cbrt(a)
     */
    public static double cbrt(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.cbrt(a);
        } else {
            double cbrt = a / 3;
            double tmp = cbrt;
            do {
                tmp = cbrt;
                cbrt = (2*tmp + (a/MathWrapper.pow(tmp, 2))) / 3;
            } while(tmp - cbrt > MathWrapper.EPSILON);

            return cbrt;
        }
    }

    /**
     * Returns absolute value of a
     * @param a Base
     * @return abs(a)
     */
    public static double abs(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.abs(a);
        } else {
            if(a >= 0.0) {
                return a;
            } else {
                return -a;
            }
        }
    }

    /**
     * Calculates factorial of a
     * @param a Base
     * @return a!
     */
    public static int factorial(int a) {
        if(a == 0.0) {
            return 1;
        } else {
            return a * MathWrapper.factorial(a - 1);
        }
    }

    /**
     * Calculates cosinus of a
     * @param a Base
     * @return cos(a);
     */
    public static double cos(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.cos(a);
        } else {
            double cos = 1;

            for(int i = 2; i < 10; i+=2) {
                if(i%4 == 2) {
                    cos -= (MathWrapper.pow(a, i) / MathWrapper.factorial(i));
                } else {
                    cos += (MathWrapper.pow(a, i) / MathWrapper.factorial(i));
                }
            }
            return cos;
        }
    }

    /**
     * Calculates arccos of a
     * @param a Base
     * @return acos(a)
     */
    public static double acos(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.acos(a);
        } else {
            return MathWrapper.PI / 2 - a
                    - (MathWrapper.pow(a, 3) / 6)
                    - (3*MathWrapper.pow(a, 5) / 40)
                    - (5*MathWrapper.pow(a, 7) / 112);
        }
    }
}
