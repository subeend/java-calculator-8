package calculator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

public final class StringAddCalculator {
    private StringAddCalculator() {}

    private static final Pattern CUSTOM_HEADER =
            Pattern.compile("^//(.)" + "(?:\\R|\\\\n)" + "(.*)$", Pattern.DOTALL);
    private static final Pattern NUMBER = Pattern.compile("\\d+");

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

        List<String> tokens = extractTokens(input);

        int sum = 0;
        for (String t : tokens) {
            int v = parsePositiveInt(t);
            sum += v;
        }
        return sum;
    }

    private static List<String> extractTokens(String input){
        String delimiterRegex = ",|:";
        String numbers = input;

        Matcher m = CUSTOM_HEADER.matcher(input);
        if (m.matches()) {
            String custom = Pattern.quote(m.group(1));
            numbers = m.group(2);
            delimiterRegex = delimiterRegex + "|" + custom;
        }

        String[] raw = numbers.split(delimiterRegex, -1);

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

    private static int parsePositiveInt(String token) {
        if (!NUMBER.matcher(token).matches()) {
            throw new IllegalArgumentException("올바르지 않은 형태의 문자열이에요."); // 비숫자
        }
        int v = Integer.parseInt(token);
        if (v < 0) {
            throw new IllegalArgumentException("양수만 입력할 수 있습니다: " + v);
        }
        return v;
    }
}
