package calculator;

public final class StringAddCalculator {
    private StringAddCalculator() {}

    public static int add(String input){
        if(input == null || input.isBlank()){
            return 0;
        }
        input = input.trim();

        throw new IllegalArgumentException("올바르지 않은 형태의 문자열이에요.");
    }
}
