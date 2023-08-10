import java.util.Scanner;

class ConcatenateStringsProblem {


    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder sb = new StringBuilder();

        for (String word : strings) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!Character.isDigit(ch)) {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}