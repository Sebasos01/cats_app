package PersonalUtils;

import java.util.Random;
import java.util.function.BinaryOperator;

public class NumericalUtils {

    public static BinaryOperator<Integer> randInt = (min, max)
            -> (new Random()).nextInt((max - min) + 1) + min;

}
