public class Cramer {
    public static void solve(Matriks M) {
        if (M.M == M.N-1) {
            double[][] tempA = new double[M.M][M.N-1];
            double[] b = new double[M.M];
            int i, j;
            for (i = 0; i < tempA.length; i++) {
                for (j = 0; j < tempA[0].length; j++) {
                    tempA[i][j] = M.Mat[i][j];
                }
            }
            Matriks A = new Matriks(tempA);
            for (i = 0; i < b.length; i++) {
                b[i] = M.Mat[i][M.N-1];
            }
            double detA = A.detCofactor();
            if (detA != 0) {
                for (i = 0; i < M.M; i++) {
                    Matriks Ai = A.clone(); 
                    for (int k = 0; k < b.length; k++) {    
                        Ai.Mat[k][i] = b[k];
                    }
                    double detAi = Ai.detCofactor();
                    System.out.println("X" + (i+1) + " = " + detAi / detA);
                }
            }
            else {
                System.out.println("Determinan matriks 0, solusi tidak dapat dicari dengan metode Cramer");
            }   
        }
        else {
            System.out.println("Banyak peubah dan persamaan SPL berbeda, solusi tidak dapat dicari dengan metode Cramer");
        }
    }
}
