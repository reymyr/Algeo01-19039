import java.util.Scanner;
import java.io.File;

public class DriverMatriks {
    public static void main(String[] args) {
        int m, n, i, j, choice;
        Matriks mat;
        Scanner in = new Scanner(System.in);
        System.out.println("1. Input from keyboard");
        System.out.println("2. Input from file"); 
        System.out.print("Choice: ");
        choice = Integer.parseInt(in.nextLine());
        if (choice == 1) {
            System.out.print("m: ");
            m = in.nextInt();
            System.out.print("n: ");
            n = in.nextInt();
            double[][] A = new double[m][n+1];
            for(i = 0; i < m; i++) {
                for (j = 0; j < n+1; j++) {
                    if (j != n) {
                        System.out.print("Koefisien a"+(i+1)+(j+1)+": ");
                    }
                    else {
                        System.out.print("Konstanta b"+(i+1)+": ");
                    }
                    A[i][j] = in.nextDouble();
                }
            }
            mat = new Matriks(A);
        }
        else if (choice == 2) {
            System.out.print("File name: ");
            String filename = in.nextLine();
            File inputFile = new File("../test/"+filename+".txt");
            mat = new Matriks(inputFile);
        }
        else {
            mat = new Matriks(1, 1);
        }
        
        //mat.printMatriks();
        //System.out.println(mat.M);
        //System.out.println(mat.N);

        //Cramer.solve(mat);
        SPLInverse.solve(mat);
        
        Matriks cfc = mat.getCofactorMatriks();
        cfc.printMatriks();
        System.out.println();

        System.out.println("Inverse cofactor");
        Matriks inv = mat.inverseCofactor();
        inv.printMatriks();
        System.out.println();

        System.out.println("Inverse gj");
        mat.inverseGaussJordan();
        System.out.println();
        

        System.out.println("Cofactor");
        System.out.println(mat.detCofactor());

        System.out.println();
        mat.printMatriks();

        System.out.println();
        mat.gauss();
        mat.printMatriks();
        System.out.println();
        mat.gaussJordan();
        mat.printMatriks();
        System.out.println();
        in.close();
    }
}