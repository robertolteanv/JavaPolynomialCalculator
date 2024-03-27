package userinterface;

import datamodels.Polynomial;
import logic.Operations;

import javax.swing.*;
import java.awt.*;

public class PolynomialCalculator extends JFrame {
    public final JTextField polynomialField1;
    public final JTextField polynomialField2;
    public final JTextField resultField;
    public final JComboBox<String> operationComboBox;

    public PolynomialCalculator() {
        setTitle("Polynomial Calculator v.1.2.1");
        setSize(750, 210);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 3));

        polynomialField1 = new JTextField();
        polynomialField2 = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        operationComboBox = new JComboBox<>(new String[]{"Addition", "Subtraction", "Multiplication",
                                                         "Division", "Derivative for first polynomial (add 'x' for second polynomial)",
                                                         "Integration for first polynomial (add 'x' for second polynomial)"});

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> calculate());

        operationComboBox.setBackground(Color.decode("#c8f7c1"));

        calculateButton.setBackground(Color.decode("#D0DAD0"));
        mainPanel.setBackground(Color.decode("#DDDDDD"));


        mainPanel.add(new JLabel("      Polynomial 1: "));
        mainPanel.add(polynomialField1);
        mainPanel.add(new JLabel("      Polynomial 2: "));
        mainPanel.add(polynomialField2);
        mainPanel.add(new JLabel("      Operation: "));
        mainPanel.add(operationComboBox);
        mainPanel.add(new JLabel("      Result: "));
        mainPanel.add(resultField);
        mainPanel.add(new JLabel("      Enter this polynomial form: ...ax^2+bx^1+cx^0"));
        mainPanel.add(calculateButton);

        add(mainPanel);
    }
    //
    public boolean validatePolynomial(String polynomial) {
        // this regex: ([+-]?(?:(?:\d+x\^\d+)|(?:\d+x)|(?:\d+)|(?:x))) ???
        String regex = "^([-+]?\\d*x(\\^\\d+)?([-+](?!$)|$))+";
        return polynomial.matches(regex);
    }

    public void calculate() {
        String polynomial1 = polynomialField1.getText();
        String polynomial2 = polynomialField2.getText();
        String operation = (String) operationComboBox.getSelectedItem();

        if (!validatePolynomial(polynomial1) || !validatePolynomial(polynomial2)) {
            JOptionPane.showMessageDialog(this, "Please enter valid polynomial expressions.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Polynomial p1 = new Polynomial(polynomial1);
        Polynomial p2 = new Polynomial(polynomial2);
        //int k=0;
        Polynomial result = null;
        try {
            assert operation != null;
            result = switch (operation) {
                case "Addition" -> Operations.add(p1, p2);
                case "Subtraction" -> Operations.subtract(p1, p2);
                case "Multiplication" -> Operations.multiply(p1, p2);
                case "Division" -> Operations.divide(p1, p2);
                case "Derivative for first polynomial (add 'x' for second polynomial)" -> Operations.derivative(p1);
                case "Integration for first polynomial (add 'x' for second polynomial)" -> Operations.integrate(p1);
                default -> result;
            };
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        if (result != null) {
            resultField.setText(result.toString());
        } else {
            resultField.setText("Error: Unable to calculate result");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PolynomialCalculator().setVisible(true));
    }
}
