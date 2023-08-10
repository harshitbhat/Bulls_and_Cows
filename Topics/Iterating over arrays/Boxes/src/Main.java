import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int size = 3;

        Scanner sc = new Scanner(System.in);
        int[] a = new int[size];
        int[] b = new int[size];

        for (int i = 0; i < size; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < size; i++) {
            b[i] = sc.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);

        if(a[0] > b[0] && a[1] > b[1] && a[2] > b[2]) {
            System.out.println("Box 1 > Box 2");
        } else if (b[0] > a[0] && b[1] > a[1] && b[2] > a[2]) {
            System.out.println("Box 1 < Box 2");
        } else {
            System.out.println("Incompatible");
        }

    }
}