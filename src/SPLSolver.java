public class SPLSolver {

    public static void cramer(Matriks M) {
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

    public static void gauss(Matriks M) {
        M.gauss();
        boolean hasSolution = false;
        int k = 0;
        while (!hasSolution && k < M.N-1) {
            if (M.Mat[M.M-1][k] != 0) {
                hasSolution = true;
            }
            k++;
        }
        if (!hasSolution) {
            if (M.Mat[M.M-1][M.N-1] == 0) {
                hasSolution = true;
            }
        }

        if (hasSolution) {
            if (M.rowZero(M.M-1) || M.N-1 > M.M) {
                System.out.println("SPL memiliki banyak solusi");
            }
            else {
                double[] solutions = new double[M.N-1];
                for (int i = M.N-2; i >= 0; i--) {
                    solutions[i] = M.Mat[i][M.N-1];

                    for (int j = i+1; j < M.N-1; j++) {
                        solutions[i] -= M.Mat[i][j]*solutions[j];
                    }
                }
                for (int i = 0; i < solutions.length; i++) {
                    System.out.println("X" + (i+1) + " = " + solutions[i]);
                }
            }
        }
        else {
            System.out.println("SPL tidak memiliki solusi");
        }
    }

    public static void gaussJordan(Matriks M) {
        M.gaussJordan();
        boolean hasSolution = false;
        int j = 0;
        while (!hasSolution && j < M.N-1) {
            if (M.Mat[M.M-1][j] != 0) {
                hasSolution = true;
            }
            j++;
        }
        if (!hasSolution) {
            if (M.Mat[M.M-1][M.N-1] == 0) {
                hasSolution = true;
            }
        }

        if (hasSolution) {
            if (M.rowZero(M.M-1) || M.N-1 > M.M) {
                System.out.println("SPL memiliki banyak solusi");
            }
            else {
                for (int i = 0; i < M.N-1; i++) {
                    System.out.println("X" + (i+1) + " = " + M.Mat[i][M.N-1]);
                }

            }
        }
        else {
            System.out.println("SPL tidak memiliki solusi");
        }

    }
}
