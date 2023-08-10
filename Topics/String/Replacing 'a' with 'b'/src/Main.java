import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String str = scanner.nextLine();

        final String toReplace = "a";
        final String replaceWith = "b";

        str = str.replace(toReplace, replaceWith);

        System.out.println(str);

    }
}