package datamodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Polynomial {
    // Map to store the terms of the polynomial
    private final Map<Integer, Integer> terms;
    // Constructor to create a polynomial from a string
    public Polynomial(String polynomialString) {
        terms = new HashMap<>();
        parsePolynomial(polynomialString);
    }

    // Method to add a term to the polynomial
    public void addTerm(int exponent, int coefficient) {
        terms.put(exponent, coefficient);
    }

    // Method to get the coefficient of a term with a given exponent
    public int getCoefficient(int exponent) {
        return terms.getOrDefault(exponent, 0);
    }

    // Method to parse a polynomial string and populate the polynomial
    private void parsePolynomial(String polynomial) {
        String[] terms = polynomial.split("\\s*(?=[+-])"); // Split on '+' or '-'
        for (String term : terms) {
            if (!term.isEmpty()) {
                String[] parts = term.split("x\\^?"); // Split on 'x' or 'x^'
                if (parts.length == 2) {
                    int exponent = Integer.parseInt(parts[1]);
                    int coefficient = parts[0].isEmpty() ? 1 : Integer.parseInt(parts[0]);
                    addTerm(exponent, coefficient);
                } else if (parts.length == 1) {
                    int exponent;
                    int coefficient = parts[0].isEmpty() ? 1 : Integer.parseInt(parts[0]);
                    if (term.contains("x")) {
                        exponent = 1;
                    } else {
                        exponent = 0;
                    }
                    addTerm(exponent, coefficient);
                }
            }
        }
    }

    // Method to retrieve the terms of the polynomial
    public Map<Integer, Integer> getTerms() {
        return terms;
    }
    //
    @Override // Method to convert the polynomial to a string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Sort the terms by exponent in descending order
        List<Map.Entry<Integer, Integer>> sortedTerms = new ArrayList<>(terms.entrySet());
        sortedTerms.sort((entry1, entry2) -> Integer.compare(entry2.getKey(), entry1.getKey()));

        boolean firstTerm = true;
        for (Map.Entry<Integer, Integer> entry : sortedTerms) {
            int exponent = entry.getKey();
            int coefficient = entry.getValue();
            if (coefficient != 0) {
                if (!firstTerm && coefficient > 0) {
                    sb.append("+");
                }
                sb.append(String.format("%dx^%d ", coefficient, exponent));
                firstTerm = false;
            }
        }
        return sb.toString();
    }
}
