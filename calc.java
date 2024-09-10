import java.util.Scanner;

public class SimpleAlgebraSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String equation;

        while (true) {
            System.out.println("enter equation or exit: ");
            equation = scanner.nextLine(); // more flexible input

            if (equation.equalsIgnoreCase("exit")) {
                break;
            }

            if (!equation.contains("=")) {
                equation += "=x"; // in case expression like 3+3 is entered, changes to 3+3=x
            }

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
                    System.out.println("error!!!!!");
                }
            } else {
                double x;
                if (isRightX) { // so if 3+3 or similar was input
                    x = constant / coefficientX; // 6/1
                } else {
                    right = Double.parseDouble(rightOG);
                    x = (right - constant) / coefficientX; // the eq for basic algebra, i thought this would be harder but no its this easy
                }
                System.out.println("x: " + x);
            }
        }
        scanner.close();
    }
}
