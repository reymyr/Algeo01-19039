import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Matriks {
    int M;
    int N;
    float[][] Mat;

    // Membentuk matriks kosong berukuran MxN
    public Matriks(int M, int N) { 
        int i, j;
        this.M = M;
        this.N = N;
        this.Mat = new float[M][N];
        for(i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                this.Mat[i][j] = 0;
            }
        }
    }

    // Membaca matriks dari sebuah file
    public Matriks(File f) {
        try {
            int i, j;
            int rows = 0, cols = 0;
            String row = "";
            Scanner rowScanner = new Scanner(f);
            while (rowScanner.hasNextLine()) {
                rows++;
                row = rowScanner.nextLine();
            }
            Scanner colScanner = new Scanner(row);
            while (colScanner.hasNextFloat()) {
                cols++;
                colScanner.nextFloat();
            }
            rowScanner.close();
            colScanner.close();
            this.M = rows;
            this.N = cols;
            this.Mat = new float[rows][cols];
            Scanner in = new Scanner(f);
            for(i = 0; i < rows; i++) {
                for (j = 0; j < cols; j++) {
                    this.Mat[i][j] = in.nextFloat();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    // Membentuk matriks dari array 2 dimensi
    public Matriks(float[][] A) {
        this.M = A.length;
        this.N = A[0].length;
        this.Mat = A;
    }

    // Menerima input matriks dari pengguna
    public void inputMatriks() {
        int i, j;
        Scanner in = new Scanner(System.in);
        for(i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                this.Mat[i][j] = in.nextFloat();
            }
        }
    }

    public Matriks clone() {
        Matriks MatClone = new Matriks(this.M, this.N);
        for(int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                MatClone.Mat[i][j] = this.Mat[i][j];
            }
        }
        return MatClone;
    }

    // Menuliskan matriks
    public void printMatriks() {
        int i, j;
        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                System.out.print(Mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void gauss() {

    }
    public void gaussJordan() {

    }

    // Menghitung determinan matriks dengan metode reduksi baris
    // Prekondisi: Matriks persegi
    public void detRowReduction() {

    }

    // Menghitung determinan matriks dengan metode ekspansi kofaktor
    // Prekondisi: Matriks persegi
    public float detCofactor() {
        if (M == 0)
        {
            return 0;
        }
        else if (M == 1)
        {
            return Mat[0][0];
        }
        else
        {
            int i, j, k, tempKol;
            float cofactor;
            float det = 0;
            int sign = 1;
    
            for (k = 0; k < N; k++)
            {
                Matriks temp = new Matriks(M-1, N-1);
                cofactor = sign*Mat[0][k];
                for (i = 1; i < M; i++)
                {
                    tempKol = 0;
                    for (j = 0; j < N; j++)
                    {
                        if (j != k)
                        {
                            temp.Mat[i-1][tempKol] = this.Mat[i][j];
                            tempKol++;
                        }
                    }
                }
                det += cofactor*temp.detCofactor();
                sign *= (-1);
            }
            return det;
        }
    }
}
