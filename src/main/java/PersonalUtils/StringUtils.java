package PersonalUtils;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class StringUtils {

    // Deletes the first and last character of a string
    public static UnaryOperator<String> trimFL = str -> {
        String str2 = str.substring(1);
        return str2.substring(0, str2.length() - 1);
    };

    public static BiFunction<byte[], String, String> bytesToString = (bytes, connector) -> {
        StringBuilder str = null;
        if (bytes.length > 0){
            str = new StringBuilder(String.valueOf(bytes[0]));
            for (int i = 1; i < (bytes.length); i++) str.append(connector).append(String.valueOf(bytes[i]));
        }
        return str == null ? null : str.toString();
    };

    public static  BiFunction<String, String, byte[]> stringToBytes = (str, connector) -> {
        byte[] bytes = null;
        if (str.length() > 0){
            String[] b = str.split(connector);
            bytes = new byte[b.length];
            for (int i = 0; i < b.length; i++) bytes[i] = Byte.parseByte(b[i]);
        }
        return bytes;
    };
}
