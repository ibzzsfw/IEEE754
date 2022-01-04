
/**
 * Java program to convert a real value to IEEE 754 Floating Point double precision Representation.
 *
 * Suppakorn Rakna 
 *
 */

import java.util.Scanner;

public class Lab2 {

    static int BITS = 64;
    static int EXPONENT_LENGHT = 11;
    static int MANTISSA_LENGTH = 52;
    static int BIAS = 1023;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double number = scanner.nextDouble();

        String IEEE754 = IEEE754Double(number);
        System.out.println(IEEE754);

        scanner.close();
    }

    /**
     * Algorithm
     * 
     * @param number A real number
     * @return string of IEEE 754 Floating Point double precision Representation
     */
    private static String IEEE754Double(double number) {

        /** Setting sign bit */
        String sign = (Double.toString(number).charAt(0) == '-') ? "1" : "0";

        /** Converting Integer part of real number to Binary */
        String intPart = integerToBinary((int) Math.abs(number));

        /** Convert a fraction to binary form */
        String fractionPart = fractionToBinary(number);

        /** Prepare binary representation without '.' */
        String binary = intPart + fractionPart;

        /** Determine exponent */
        String exponent = (number == 0) ? "" : integerToBinary((intPart.length() - 1) - binary.indexOf("1") + BIAS);
        while (exponent.length() != EXPONENT_LENGHT) {
            exponent = "0" + exponent;
        }

        /** Prepare mantissa */
        String mantissa = binary.substring(binary.indexOf("1") + 1, binary.indexOf("1") + 1 + MANTISSA_LENGTH);

        /** IEEE754 standard double precision */
        return (sign + " " + exponent + " " + mantissa);
    }

    /**
     * Converting Interger part of real number to Binary
     * 
     * @param number non-negative integer
     * @return Bit string of @param
     */
    private static String integerToBinary(int number) {

        return (number == 0 || number == 1) ? String.valueOf(number) : integerToBinary(number / 2) + (number % 2);
    }

    /**
     * Convert a fraction to binary form
     * 
     * @param number A real number
     * @return String of Binary representation of fraction of number
     */
    private static String fractionToBinary(double number) {

        String binary = new String();
        double fraction = Math.abs(number) % 1;
        int iterate = 52 + 1 - ((number == 0) ? 1 : (int) (Math.log10(Math.abs(number)) / Math.log10(2)));

        for (int bitCount = 0; bitCount < iterate; bitCount++) {
            fraction *= 2;
            binary += (fraction >= 1) ? "1" : "0";
            fraction = (fraction >= 1) ? fraction - 1 : fraction;
        }
        return binary;
    }
}
