import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String str = scanner.nextLine();
        System.out.println(str.charAt(0) == 'J' || str.charAt(0) == 'j');
    }
}