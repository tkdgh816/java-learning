package p260917.ex5;

import java.util.function.BiFunction;

public enum Operation {
    ADD((a, b) -> a + b),
    SUBTRACT((a, b) -> a - b),
    MULTIPLY((a, b) -> a * b),
    DIVIDE((a, b) -> a / b);

    private BiFunction<Integer, Integer, Integer> expression;

    Operation(BiFunction<Integer, Integer, Integer> expression) {
        this.expression = expression;
    }

    public int calculate(int a, int b) {
        return expression.apply(a, b);
    }
}