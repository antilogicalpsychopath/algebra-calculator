import java.util.Scanner;

public class AlgebraCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String equation;

        while (true) {
            System.out.println("Enter equation or exit: ");
            equation = scanner.nextLine(); // more flexible input

            if (equation.equalsIgnoreCase("exit")) { // EXIT would work too if ur rlly mad at my program :(
                break;
            }

            if (equation.contains("=")) {
                String[] sides = equation.split("=");
                String leftSide = sides[0].replaceAll(" ", ""); // NO SPACES
                String rightOG = sides[1].replaceAll(" ", "");

                double right = 0;
                boolean isRightX = false;

                if (rightOG.equals("x")) {
                    isRightX = true;
                } else {
                    right = Double.parseDouble(rightOG); // convert string to double which is just BIGger float
                }

                if (leftSide.matches(".*[*/].*")) {
                    System.out.println("error NO DIVISION OR * MULTPLICATION"); // If multiplication or division is present in algebraic expressions (too hard for the puny little baby 16 yr old i am)
                } else {
                    double coefficientX = 0;
                    double constant = 0;

                    String[] parts = leftSide.split("(?=[+-])"); // splits into different parts like "3x+3" -> "3x","+3"

                    for (String part : parts) {
                        if (part.contains("x")) { // if dealing with coeff eg 3x
                            part = part.replace("x", "");
                            if (part.equals("+") || part.equals("")) { // this bc +x is positive x but just x is also positive x
                                coefficientX += 1;
                            } else if (part.equals("-")) {
                                coefficientX -= 1;
                            } else {
                                coefficientX += Double.parseDouble(part); // coeffx = coeffx+part (handles coeffeciants like 7x or -13x)
                            }
                        } else { // if dealing with not coeff eg 3
                            constant += Double.parseDouble(part);
                        }
                    }

                    if (coefficientX == 0) {
                        if (isRightX) {
                            System.out.println("x: " + constant);
                        } else {
                            System.out.println("error!!!"); // grrr WHY NO X?????????
                        }
                    } else {
                        double x;
                        if (isRightX) { // so if 3+3 or similar was input
                            x = constant / coefficientX; // 6/1
                        } else {
                            right = Double.parseDouble(rightOG);
                            x = (right - constant) / coefficientX; // the eq for basic algebra
                        }
                        System.out.println("x: " + x);
                    }
                }
            } else if (equation.matches("^[0-9]+([*/][0-9]+)*$")) {
                // regex is confusing to look at but pretty simple: just checking that it looks like this: 87*94 or 7/1
                String[] parts = equation.split("(?=[*/])"); // split * or / for mult or div
                double result = 1;
                boolean isMultiplying = true;

                for (String part : parts) { // literally just normal  */ calculator; making an actually correct algebra calculator with division and multiplication is hard
                    if (part.contains("*")) {
                        isMultiplying = true;
                        part = part.replace("*", "");
                    } else if (part.contains("/")) {
                        isMultiplying = false;
                        part = part.replace("/", "");
                    }
                    double value = Double.parseDouble(part);
                    if (isMultiplying) {
                        result *= value;
                    } else {
                        result /= value;
                    }
                }
                System.out.println("x: " + result);
            } else {
                System.out.println("error!!"); // ew get out
            }
        }
        scanner.close();
    }
}
