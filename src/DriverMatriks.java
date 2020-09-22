import java.util.Scanner;

public class DriverMatriks {
    public static void main(String[] args) {
        int m, n, i, j;
        Scanner in = new Scanner(System.in);
        System.out.print("m: ");
        m = in.nextInt();
        System.out.print("n: ");
        n = in.nextInt();
        float[][] A = new float[m][n+1];
        for(i = 0; i < m; i++) {
            for (j = 0; j < n+1; j++) {
                if (j != n) {
                    System.out.print("Koefisien a"+(i+1)+(j+1)+": ");
                }
                else {
                    System.out.print("Konstanta b"+(i+1)+": ");
                }
                A[i][j] = in.nextFloat();
            }
        }
        Matriks mat = new Matriks(A);
        mat.printMatriks();
        in.close();
    }
}
