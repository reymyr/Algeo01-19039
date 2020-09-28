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

    // mencari posisi leading coefficient di baris i
    public int leadingCoef(int i){
        boolean found = false;
        int j = 0;

        while (!found && (j < N)){
            if (this.Mat[i][j] != 0){
                found = true;
            }
            else{
                j += 1;
            }
        }

        return j;
    }

    // membuat matriks eselon baris sesuai metode gauss
    public void gauss(){
        int i, j, k, key;
        double coef, tmp;

        // OBE menjadi eselon baris
        for (i = 0; i < M; i++){
            for (k = i + 1; k < M; k++){
                while (this.Mat[k][this.leadingCoef(i)] != 0){
                    coef = this.Mat[k][this.leadingCoef(i)] / this.Mat[i][this.leadingCoef(i)];
                    for (j = this.leadingCoef(i); j < N; j++){
                        this.Mat[k][j] -= coef * this.Mat[i][j];
                    }
                }
            }
        }

        // pembuatan leading one
        for (i = 0; i < M; i++){
            for (j = this.leadingCoef(i); j < N; j++){
                this.Mat[i][j] *= 1 / this.Mat[i][this.leadingCoef(i)];
            }
        }

        // sort matriks
        for (i = 0; i <=\ M; i++){
            for (k = i+1; k < M; j++){
                if (this.leadingCoef(i) > this.leadingCoef(k)){
                    tmp = this.Mat[i][k]
                } 
            }
        }

    }

    // gauss-jordan, matriks dijadikan eselon baris tereduksi
    public void gaussJordan() {
        int i, j, k;
        double coef;

        this.gauss();

        for (i = 0; i < M; i++){
            if (this.leadingCoef(i) != 0){
                for (k = 0; k < M; k++){
                    if (k != i){
                        coef = this.Mat[k][this.leadingCoef(i)];
                        for (j = 0; j < N; j++){
                            this.Mat[k][j] += coef * this.Mat[i][j];
                        }
                    }
                }
            }

        }
    }

    // Menghitung determinan matriks dengan metode reduksi baris
    // Prekondisi: Matriks persegi
    public void detRowReduction() {
        if (M == 0){
            return 0;
        }
        else if (M == 1){
            return Mat[0][0];
        }
        else { // Reduksi baris, membentuk matriks segitiga atas
            int i,j,k,p;
            float det = 1;
            float x;
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
                                float temp;
                                jumlahSwap = jumlahSwap + 1;
                                for(p = 0; p < M; p++){
                                    temp = Mat[i][p];
                                    Mat[i][p] = Mat[M-1][p];
                                    Mat[M-1][p] = temp;
                                }
                            }
                            x = Mat[j][i] / Mat[i][i];
                            for (k = 0; k < M; k++){
                                Mat[j][k] = Mat[j][k] - (Mat[i][k] * x);
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
