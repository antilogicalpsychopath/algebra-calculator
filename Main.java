import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GUI();
    }

    // eshaan
    private static void algebraCalculator(String inputText, JLabel resultLabel) {
        Scanner scanner = new Scanner(inputText);
        String equation;
        equation = scanner.next(); // input

        if (equation.equalsIgnoreCase("exit")) { // EXIT would work too if ur rlly mad at my program :(
            scanner.close();
            return;
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
                resultLabel.setText("error NO DIVISION OR * MULTPLICATION in alg"); // if multiplication or division is present in algebraic expressions THEN NO!!! (too hard for the puny little baby 16 yr old i am)
            } else {
                double coeffX = 0;
                double constant = 0;

                String[] parts = leftSide.split("(?=[+-])"); // regex that splits into different parts like "3x+3" -> "3x","+3"

                for (String part : parts) {
                    if (part.contains("x")) { // if dealing with coeff eg 3x
                        part = part.replace("x", "");
                        if (part.equals("+") || part.equals("")) { // this bc +x is positive x but just x is also positive x
                            coeffX += 1;
                        } else if (part.equals("-")) { // -x
                            coeffX -= 1;
                        } else {
                            coeffX += Double.parseDouble(part); // coeffx = coeffx+part (handles coeffeciants like 7x or -13x which would be 7 and 13 in this context)
                        }
                    } else { // if dealing with not coeff (constant) eg 3 that never had an x in it
                        constant += Double.parseDouble(part);
                    }
                }

                if (coeffX == 0) { // if no coeff 8=8 8=9 8=x
                    if (isRightX) { //like if 3=x
                        resultLabel.setText("x = " + String.valueOf(constant));
                    } else {
                        resultLabel.setText("error!!!!"); // grrr WHY NO X?????????
                    }
                } else { // actual algebra time!!
                    double x;
                    if (isRightX) { // so if 3x=3 or similar was input
                        x = constant / coeffX; // 3/3 if above example
                    } else {
                        right = Double.parseDouble(rightOG);
                        x = (right - constant) / coeffX; // the eq for basic algebra
                    }
                    resultLabel.setText("x = " + String.valueOf(x)); // type conversion to string
                }
            }
        } else {
            scanner.close();
            return;
        }

        scanner.close();
    }

    // namith w/ help from eshaan
    private static void arithmeticCalculator(String equation, JLabel resultLabel) {
    try { // what if everything breaks???
        String[] addSParts = equation.split("(?=[+-])"); //creating an array using regex to seperate the string based on + - seperators

        StringBuilder middleBoi = new StringBuilder(); //awesome thing that makes strings cool where you can like reverse strings and stuff (main feature is cool appending with indexes if u want but i dont use that here, i just use appending w/o indexes)
        for (String part : addSParts) { // for every item int the addspart array (eg 3x and 3) and one of those (eg 3x) = part as an argument in this loop
            double result = 42; // answer to life the universe and everything (random number cuz im gonna redef later)
            boolean hasOperators = false; // checks if it has * or /

            String[] mulDivParts = part.split("(?=[*/])");

            if (mulDivParts.length > 0) { // is it a mult/div eq or no
                result = Double.parseDouble(mulDivParts[0].trim()); // it is redefined now :( no more 42 (redeffed to first number of the parts of the eq, trim removes extra spaces), so if 3*3, then this would be 3 and Double.parseDouble(mulDivParts[1].trim()) would be == GUESS WHAT ITS AN ERROR because the unparsed would be "*3" so u gotta remove the * and after doing that it will == 3 as well. we're gonna do this in the next loop

                for (int i = 1; i < mulDivParts.length; i++) {
                    String mulDivPart = mulDivParts[i].trim(); // prep time

                    if (mulDivPart.startsWith("*")) { // see i told u we were gonna do this
                        mulDivPart = mulDivPart.substring(1).trim(); // substring is part o' string, basically just removed the * from it so we just get the number to convert to double. this is bad coding it would have made more sense to use replace("*", ""); but im just like that i guess
                        result *= Double.parseDouble(mulDivPart); // ew actual math gross
                        hasOperators = true;
                    } else if (mulDivPart.startsWith("/")) {
                        mulDivPart = mulDivPart.replace("/", "").trim(); // im just that cool, i use different replace techniques for each thing (everyone reading my code hates me rn)
                        result /= Double.parseDouble(mulDivPart); // ick
                        hasOperators = true;
                    }
                }
            }

            if (hasOperators) {
                middleBoi.append(result); //this is why stringbuilder so awesome
            } else {
                middleBoi.append(part); // no operators? :skull:
            }
        }

        String bigBoi = middleBoi.toString();
        String[] addSPartsFinal = bigBoi.split("(?=[+-])");

        double finaleBoi = 0;
        for (String part : addSPartsFinal) {
            if (part.startsWith("-")) {
                finaleBoi -= Double.parseDouble(part.substring(1).trim());
            } else {
                finaleBoi += Double.parseDouble(part.trim()); // even if a normal number is put into this, for example, a middleBoi that had gone through the * and / and had come out w/ a final answer, because finaleboi is set to 0 meaning that it's just adding middleBoi to finaleboi normally :D
            }
        }

        resultLabel.setText("x = " + finaleBoi);
    } catch (Exception e) { // this (reference to line 87)
        resultLabel.setText("error!!!");
    }
}


    // sai w/ help from eshaan
    private static void GUI() { // most of this stuff self explanatory
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        JTextField textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(200, 25));
        textField.setHorizontalAlignment(JTextField.CENTER);
        JButton button = new JButton("solve");
        JLabel resultLabel = new JLabel("");
        JLabel Instr = new JLabel("Algebra Calculator");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                if (inputText.contains("=")) {
                    algebraCalculator(inputText, resultLabel);
                } else {
                    arithmeticCalculator(inputText, resultLabel);
                }
            }
        });

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalPanel.add(Instr);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        verticalPanel.add(textField);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(button);
        verticalPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        verticalPanel.add(resultLabel);
        frame.setBackground(Color.decode("#7ba0b0"));
        verticalPanel.setBackground(Color.decode("#7ba0b0"));

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Instr.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(verticalPanel);
        frame.setVisible(true);
    }
}
