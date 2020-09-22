import java.util.Scanner;

public class DriverMatriks {
    public static void main(String[] args) {
        int m, n;
        Scanner in = new Scanner(System.in);
        System.out.print("m: ");
        m = in.nextInt();
        System.out.print("n: ");
        n = in.nextInt();
        Matriks mat = new Matriks(m, n);
        mat.printMatriks();
    }
}
