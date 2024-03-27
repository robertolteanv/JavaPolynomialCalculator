package logic;

import datamodels.Polynomial;

import java.util.Map;

public class Operations {
    public static Polynomial add(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial("");

        // Add terms from polynomial1
        for (Map.Entry<Integer, Integer> entry : polynomial1.getTerms().entrySet()) {
            int exponent = entry.getKey();
            int coefficient = entry.getValue();
            int currentCoefficient = polynomial2.getCoefficient(exponent);
            result.addTerm(exponent, currentCoefficient + coefficient);
        }

        // Add terms from polynomial2 that are not present in polynomial1
        for (Map.Entry<Integer, Integer> entry : polynomial2.getTerms().entrySet()) {
            int exponent = entry.getKey();
            int coefficient = entry.getValue();
            if (!polynomial1.getTerms().containsKey(exponent)) {
                result.addTerm(exponent, coefficient);
            }
        }

        return result;
    }

    public static Polynomial subtract(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial("");

        // Subtract terms of polynomial2 from polynomial1
        for (Map.Entry<Integer, Integer> entry : polynomial1.getTerms().entrySet()) {
            int exponent = entry.getKey();
            int coefficient = entry.getValue();
            int currentCoefficient = polynomial2.getCoefficient(exponent);
            result.addTerm(exponent, coefficient - currentCoefficient);
        }

        // Add terms from polynomial2 that are not present in polynomial1
        for (Map.Entry<Integer, Integer> entry : polynomial2.getTerms().entrySet()) {
            int exponent = entry.getKey();
            int coefficient = entry.getValue();
            if (!polynomial1.getTerms().containsKey(exponent)) {
                result.addTerm(exponent, -coefficient);
            }
        }

        return result;
    }

    public static Polynomial multiply(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial("");

        for (Map.Entry<Integer, Integer> term1 : polynomial1.getTerms().entrySet()) {
            for (Map.Entry<Integer, Integer> term2 : polynomial2.getTerms().entrySet()) {
                int exponent = term1.getKey() + term2.getKey();
                int coefficient = term1.getValue() * term2.getValue();
                int currentCoefficient = result.getTerms().getOrDefault(exponent, 0);
                result.addTerm(exponent, currentCoefficient + coefficient);
            }
        }

        return result;
    }
    public static Polynomial divide(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial("");
        Polynomial temp = new Polynomial("");
        for(Map.Entry<Integer, Integer> term1 : polynomial1.getTerms().entrySet()){
            for(Map.Entry<Integer, Integer> term2 : polynomial2.getTerms().entrySet()){
                int exponent = term1.getKey() - term2.getKey();
                int coefficient = term1.getValue() / term2.getValue();
                int currentCoefficient = result.getTerms().getOrDefault(exponent, 0);
                result.addTerm(exponent, currentCoefficient + coefficient);
                temp.addTerm(exponent, currentCoefficient + coefficient);
            }
        }

        return result;
    }
    public static Polynomial derivative(Polynomial polynomial1) {
        Polynomial result = new Polynomial("");
        for(Map.Entry<Integer, Integer> term1 : polynomial1.getTerms().entrySet()){
            int exponent = term1.getKey() - 1;
            int coefficient = term1.getValue() * term1.getKey();
            result.addTerm(exponent, coefficient);
        }
        return result;
    }

    public static Polynomial integrate(Polynomial polynomial1) {
        Polynomial result = new Polynomial("");
        for(Map.Entry<Integer, Integer> term1 : polynomial1.getTerms().entrySet()){
            int exponent = term1.getKey() + 1;
            int coefficient = term1.getValue() / term1.getKey()/2;
            result.addTerm(exponent, coefficient);
        }
        return result;
    }
}
