import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LinearRegression {
    Matriks mat;

    public LinearRegression() {
        int choiceInput;
        Scanner in = new Scanner(System.in);
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file");
        System.out.print("Choice (1/2): ");
        choiceInput = Integer.parseInt(in.nextLine());
        if (choiceInput == 1){
            int n, col;
            System.out.print("n (jumlah peubah x): ");
            n = in.nextInt();
            System.out.print("k (): ");
            col = in.nextInt();
            double[][] A = new double[n][col+1];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < col+1; j++){
                    if (j != n){
                        System.out.print("Masukkan x" + (i + 1) + (j + 1) + ": ");
                    }
                    else{
                        System.out.print("Masukkan x" + (i + 1) + ": ");
                    }
                    A[i][j] = in.nextDouble();
                }
            }
            mat = new Matriks(A);

        }

    }

    public void LRSolve() {
        
    }
}
