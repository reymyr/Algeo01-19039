import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Interpolasi {
    Matriks mat;
    double x;

    public Interpolasi () {
        int choiceInput;
        Scanner in = new Scanner(System.in);
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file"); 
        System.out.print("Choice: ");
        choiceInput = Integer.parseInt(in.nextLine());
        if (choiceInput == 1) {
            int n;
            System.out.print("n: ");
            n = in.nextInt();
            double[][] A = new double[n][2];
            for(int k = 0; k < n; k++) {
                System.out.print("x"+k+": ");
                A[k][0] = in.nextDouble();
                System.out.print("y"+k+": ");
                A[k][1] = in.nextDouble();
            }
            mat = new Matriks(n, n+1);
            for (int i = 0; i < mat.M ;i++) {
                for (int j = 0; j < mat.N; j++) {
                    if (j != mat.N-1) {
                        this.mat.Mat[i][j] = Math.pow(A[i][0], j);
                    }
                    else {
                        this.mat.Mat[i][j] = A[i][1];
                    }
                }
            }
            System.out.print("Nilai x yang akan ditaksir: ");
            this.x = in.nextDouble();
        }
        else if (choiceInput == 2) {
            System.out.print("Nama file: ");
            String filename = in.nextLine();
            File inputFile = new File("../test/"+filename+".txt");
            try {
                int rows = 0;
                Scanner rowScanner = new Scanner(inputFile);
                while (rowScanner.hasNextLine()) {
                    rows++;
                    rowScanner.nextLine();
                }
                rowScanner.close();

                double A[][] = new double[rows][2];
                Scanner fileScanner = new Scanner(inputFile);
                for(int i = 0; i < rows; i++) {
                    for (int j = 0; j < 2; j++) {
                        A[i][j] = fileScanner.nextDouble();
                    }
                }
                fileScanner.close();

                this.mat = new Matriks(rows, rows+1);
                for (int i = 0; i < mat.M ;i++) {
                    for (int j = 0; j < mat.N; j++) {
                        if (j != mat.N-1) {
                            this.mat.Mat[i][j] = Math.pow(A[i][0], j);
                        }
                        else {
                            this.mat.Mat[i][j] = A[i][1];
                        }
                    }
                }
                System.out.print("Nilai x yang akan ditaksir: ");
                this.x = in.nextDouble();
                System.out.println();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Input tidak valid");
        }
        in.close();
    }

    public void solve() {
        this.mat.gauss();
        double[] solutions = new double[this.mat.N-1];
        for (int i = this.mat.N-2; i >= 0; i--) {
            solutions[i] = this.mat.Mat[i][this.mat.N-1];

            for (int j = i+1; j < this.mat.N-1; j++) {
                solutions[i] -= this.mat.Mat[i][j]*solutions[j];
            }
        }
        System.out.println("Persamaan polinom:");
        double res = 0;
        for (int i = 0; i < solutions.length; i++) {
            res += solutions[i]*Math.pow(this.x, i);
            System.out.print(String.format("%.4f", Math.abs(solutions[i]))+"x^"+i);
            if (i < solutions.length-1) {
                if (solutions[i+1] >= 0) {
                    System.out.print(" + ");
                }
                else {
                    System.out.print(" - ");
                }
            }
        }
        System.out.println("\n");
        System.out.println("Nilai taksiran: "+String.format("%.4f", res));
    }
}
