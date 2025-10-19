package calculator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

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

    private static List<String> extractToken(String input){
        String delimiterRegex = ",|:";
        String numbers = input;

        Matcher m = CUSTOM_HEADER.matcher(input);
        if (m.matches()) {
            String custom = Pattern.quote(m.group(1));
            numbers = m.group(2);
            delimiterRegex = delimiterRegex + "|" + custom;
        }

        String[] raw = numbers.split(delimiterRegex);
        List<String> tokens = new ArrayList<>(raw.length);
        for (String r : raw) {
            String t = r.trim();
            if (t.isEmpty()) {
                throw new IllegalArgumentException("올바르지 않은 형태의 문자열이에요.");
            }
            tokens.add(t);
        }
        return tokens;
    }
}
