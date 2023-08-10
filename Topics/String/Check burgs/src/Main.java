import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String str = scanner.nextLine();


        if(str.length() < 4) {
            System.out.println(false);
        } else if(str.length() == 4) {
           if(str.equalsIgnoreCase("burg")) {
               System.out.println(true);
           } else {
               System.out.println(false);
           }
        } else {
            String suffix = str.substring(str.length() - 4, str.length());
            if(suffix.equalsIgnoreCase("burg")) {
                System.out.println(true);
            } else {
                System.out.println("false");
            }
        }

    }
}