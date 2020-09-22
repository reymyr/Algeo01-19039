import java.util.Scanner;

public class Matriks {
    int M;
    int N;
    float[][] Mat;

    // Membentuk matriks augmented dari SPL dengan input m dan n
    public Matriks(int M, int N) { 
        int i, j;
        this.M = M;
        this.N = N+1;
        Scanner in = new Scanner(System.in);
        this.Mat = new float[M][N+1];
        for(i = 0; i < M; i++) {
            for (j = 0; j < N+1; j++) {
                if (j != N) {
                    System.out.print("Koefisien a"+(i+1)+(j+1)+": ");
                }
                else {
                    System.out.print("Konstanta b"+(i+1)+": ");
                }
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
}
