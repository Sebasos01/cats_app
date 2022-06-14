package PersonalUtils;
import java.util.function.UnaryOperator;

public class StringUtils {

    // Deletes the first and last character of a string
    public static UnaryOperator<String> trimFL = str -> {
        String str2 = str.substring(1);
        return str2.substring(0, str2.length() - 1);
    };

}
