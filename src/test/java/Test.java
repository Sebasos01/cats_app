import PersonalUtils.StringUtils;

import java.util.function.Consumer;

public class Test {
    public static void main(String[] args) {
        byte[] bytes = {37, 17, 120, -54, -40, 63, -114, 108, 66, -13, 65, 16, -97, -110, -103, 111, 68, -112, -4, -117, -110, 123, 0, -56, 125, 58, 116, 42, 83, 118, -63, 74, -47, 89, 17, -11, -12, -69, 105, 119, -109, -111, 32, 26, -15, 100, -53, 1};
        printBytes.accept(StringUtils.stringToBytes.
                apply(StringUtils.bytesToString.apply(bytes, "_"), "_"));
        System.out.println(StringUtils.bytesToString.apply(bytes, "_"));
    }

    static Consumer<byte[]> printBytes = bytes -> {for (byte aByte : bytes) System.out.println(aByte);};
}
