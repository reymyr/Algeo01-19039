public class SPLInverse{
    public static void solve(Matriks M){
        if(M.M == M.N-1){
            double[][] tempA = new double[M.M][M.N-1];
            double[] b = new double[M.M];
            int i, j, p, q, r;
            for (i = 0; i < tempA.length; i++) {
                for (j = 0; j < tempA[0].length; j++) {
                    tempA[i][j] = M.Mat[i][j];
                }
            }
            Matriks A = new Matriks(tempA);
            for (i = 0; i < b.length; i++) {
                b[i] = M.Mat[i][M.N-1];
            }
            Matriks AInverse = A.inverseCofactor();
            double[] hasil = new double[M.M]; // hasil perkalian A inverse dengan b
            for (i = 0; i < hasil.length; i++){
                hasil[i] = 0;
            }
            for(i = 0; i < AInverse.length; i++){
                for(j = 0; j < AInverse.length; j++){
                    hasil[i] += (AInverse.Mat[i][j] * b[j]);
                }
                System.out.println("X" + (i+1) + " = " + hasil[i]);
            }
        }
        else{
            System.out.println("Banyak peubah dan persamaan SPL berbeda, solusi tidak dapat dicari dengan metode inverse");
        }
    }    
}