import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;

public class Matriks {
    int M;
    int N;
    double[][] Mat;

    // Membentuk matriks kosong berukuran MxN
    public Matriks(int M, int N) { 
        int i, j;
        this.M = M;
        this.N = N;
        this.Mat = new double[M][N];
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
            while (colScanner.hasNextDouble()) {
                cols++;
                colScanner.nextDouble();
            }
            rowScanner.close();
            colScanner.close();
            this.M = rows;
            this.N = cols;
            this.Mat = new double[rows][cols];
            Scanner in = new Scanner(f);
            for(i = 0; i < rows; i++) {
                for (j = 0; j < cols; j++) {
                    this.Mat[i][j] = in.nextDouble();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    // Membentuk matriks dari array 2 dimensi
    public Matriks(double[][] A) {
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
                this.Mat[i][j] = in.nextDouble();
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
                System.out.print(String.format("%.2f", Mat[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public void gauss() {

    }
    public void gaussJordan() {

    }

    public Matriks getCofactorMatriks() {
        int i, j, itemp, jtemp, tempRow, tempKol;
        int sign = 1;
        Matriks cofactorMat = new Matriks(this.M, this.N);
        
        for (i = 0; i < this.M; i++) {
            for (j = 0; j < this.N; j++)
            {
                Matriks temp = new Matriks(M-1, N-1);
                tempRow = 0;
                for (itemp = 0; itemp < this.M; itemp++)
                {
                    tempKol = 0;
                    for (jtemp = 0; jtemp < this.N; jtemp++)
                    {
                        if (itemp != i && jtemp != j)
                        {
                            temp.Mat[tempRow][tempKol] = this.Mat[itemp][jtemp];
                            tempKol++;
                            if (tempKol == this.N-1) {
                                tempRow++;
                            }
                        }
                    }
                    
                }
                cofactorMat.Mat[i][j] = sign*temp.detCofactor();
                sign *= (-1);
            }
        }
        return cofactorMat;
    }


    // Menghitung determinan matriks dengan metode reduksi baris
    // Prekondisi: Matriks persegi
    public double detRowReduction() {
        if (M == 0){
            return 0;
        }
        else if (M == 1){
            return Mat[0][0];
        }
        else { // Reduksi baris, membentuk matriks segitiga atas
            int i,j,k,p;
            double det = 1;
            double x;
            int jumlahSwap = 0;
            int adaNol = 0;
            int jumlahNol = 0;
            int bar = 0;
            int kol = 0;

            //cek apakah ada 0 di baris pertama
            while (adaNol == 0 && kol < M) {
                if (Mat[bar][kol] == 0) {
                    adaNol = 1;
                }
                else{
                    kol = kol + 1;
                }
            }
            //jika ada 0, cek apakah kolom tersebut isinya 0 semua, jika ya (jumlahNol == M), maka det = 0
            if(adaNol == 1){
                for (bar = 0; bar < M; bar++){
                    if(Mat[bar][kol] == 0){
                        jumlahNol = jumlahNol + 1;
                    }
                }
            }
            if(jumlahNol == M){
                return 0;
            }
            //jika tidak ada kolom yang isinya 0 semua, lanjutkan dengan reduksi baris
            else{
                for (i = 0; i < M; i++) {
                    for (j = 0; j < N; j++){
                        if (i < j){
                            if (Mat[i][i] == 0 && i != (M-1)){ 
                                double temp;
                                int q =  i;
                                int getNol = 0;
                                int barisSwap = i;
                                while (q < M && getNol == 0) {
                                    if (Mat[q][i] != 0) {
                                        getNol = 1;
                                        barisSwap = q;
                                    }
                                    else{
                                        q = q + 1;
                                    }
                                }
                                for(p = 0; p < M; p++){
                                    temp = Mat[i][p];
                                    Mat[i][p] = Mat[barisSwap][p];
                                    Mat[barisSwap][p] = temp;
                                    if (p != M-1) {
                                        jumlahSwap = jumlahSwap + 1;
                                    }
                                }
                            }
                            if (Mat[i][i] == 0){
                                det = 0;
                            }
                            else{
                                x = Mat[j][i] / Mat[i][i];
                                for (k = 0; k < M; k++){
                                    Mat[j][k] = Mat[j][k] - (Mat[i][k] * x);
                                }
                            }
                        }
                    }
                    det = det * Mat[i][i];
                }
                det = det * (Math.pow(-1, jumlahSwap));
                return det;
            }
        }
    }
    // Menghitung determinan matriks dengan metode ekspansi kofaktor
    // Prekondisi: Matriks persegi
    public double detCofactor() {
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
            double det = 0;
            Matriks cofactroMatriks = this.getCofactorMatriks();
            for (int j = 0; j < this.N; j++) {
                det += this.Mat[0][j]*cofactroMatriks.Mat[0][j];
            }
            return det;
        }
    }
}
