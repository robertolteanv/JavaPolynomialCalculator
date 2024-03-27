import org.junit.jupiter.api.Test;
import userinterface.PolynomialCalculator;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialCalculatorTest {

    @Test
    void testValidatePolynomial() {
        PolynomialCalculator calculator = new PolynomialCalculator();

        assertTrue(calculator.validatePolynomial("2x^2+3x^1+2x^0"));
        assertTrue(calculator.validatePolynomial("-x^2+3x^1-2x^0"));
        assertFalse(calculator.validatePolynomial("4x^2+3x^1+2y^1"));
        assertFalse(calculator.validatePolynomial("2x^2+3x+2-"));
    }
    @Test
    void testAddition() {
        PolynomialCalculator calculator = new PolynomialCalculator();
        calculator.polynomialField1.setText("2x^2+3x^1+2x^0");
        calculator.polynomialField2.setText("3x^2+2x^1+1x^0");
        calculator.operationComboBox.setSelectedIndex(0);
        calculator.calculate();

        String expected = "5x^2 +5x^1 +3x^0 ";
        String actual = calculator.resultField.getText();

        char[] expectedChars = expected.toCharArray();
        char[] actualChars = actual.toCharArray();

        assertArrayEquals(expectedChars, actualChars);
    }
    @Test
    void testSubtraction() {
        PolynomialCalculator calculator = new PolynomialCalculator();
        calculator.polynomialField1.setText("2x^2+3x^1+2x^0");
        calculator.polynomialField2.setText("3x^2+2x^1+1x^0");
        calculator.operationComboBox.setSelectedIndex(1);
        calculator.calculate();

        String expected = "-1x^2 +1x^1 +1x^0 ";
        String actual = calculator.resultField.getText();

        char[] expectedChars = expected.toCharArray();
        char[] actualChars = actual.toCharArray();

        assertArrayEquals(expectedChars, actualChars);
    }
    @Test
    void testMultiplication() {
        PolynomialCalculator calculator = new PolynomialCalculator();
        calculator.polynomialField1.setText("2x^2+3x^1+2x^0");
        calculator.polynomialField2.setText("3x^2+2x^1+1x^0");
        calculator.operationComboBox.setSelectedIndex(2);
        calculator.calculate();

        String expected = "6x^4 +13x^3 +14x^2 +7x^1 +2x^0 ";
        String actual = calculator.resultField.getText();

        char[] expectedChars = expected.toCharArray();
        char[] actualChars = actual.toCharArray();

        assertArrayEquals(expectedChars, actualChars);
    }
    @Test
    void testDivision() {
        PolynomialCalculator calculator = new PolynomialCalculator();
        calculator.polynomialField1.setText("2x^2+3x^1+2x^0");
        calculator.polynomialField2.setText("1x^1");
        calculator.operationComboBox.setSelectedIndex(3);
        calculator.calculate();

        String expected = "2x^1 +3x^0 +2x^-1 ";
        String actual = calculator.resultField.getText();

        char[] expectedChars = expected.toCharArray();
        char[] actualChars = actual.toCharArray();

        assertArrayEquals(expectedChars, actualChars);
    }
    @Test
    void testDerivative() {
        PolynomialCalculator calculator = new PolynomialCalculator();
        calculator.polynomialField1.setText("2x^2+3x^1+2x^0");
        calculator.polynomialField2.setText("x^1");
        calculator.operationComboBox.setSelectedIndex(4);
        calculator.calculate();

        String expected = "4x^1 +3x^0 ";
        String actual = calculator.resultField.getText();

        char[] expectedChars = expected.toCharArray();
        char[] actualChars = actual.toCharArray();

        assertArrayEquals(expectedChars, actualChars);
    }


}