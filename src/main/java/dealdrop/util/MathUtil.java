package dealdrop.util;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Functions;

public class MathUtil {
    public static double evaluateMathFunction(String function, double x) {
        Expression expression = new ExpressionBuilder(function)
                .variable("x")
                .build();
        expression.setVariable("x", x);

        // Evaluate the expression
        double result = expression.evaluate();
        return result;
    }
}
