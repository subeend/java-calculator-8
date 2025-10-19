package calculator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class StringAddCalculator {
    private StringAddCalculator() {}

    private static final Pattern CUSTOM_HEADER = Pattern.compile("//(.)\\n(.*)", Pattern.DOTALL);

    public static int add(String input){
        if(input == null || input.isBlank()){
            return 0;
        }
        input = input.trim();

        if(input.startsWith("//")){
            Matcher m = CUSTOM_HEADER.matcher(input);
            if(!m.matches()){
                throw new IllegalArgumentException("올바르지 않은 형태의 문자열이에요.");
            }
        }

        throw new IllegalArgumentException("올바르지 않은 형태의 문자열이에요.");
    }
}
