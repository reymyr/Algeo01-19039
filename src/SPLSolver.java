import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.*;

public class SPLSolver {
    Matriks mat;
    double[] solutions;
    boolean toFile;

    public SPLSolver() {
        int i, j, choiceInput;
        Scanner in = new Scanner(System.in);
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file"); 
        System.out.print("Choice: ");
        choiceInput = Integer.parseInt(in.nextLine());
        if (choiceInput == 1) {
            int m, n;
            System.out.print("m: ");
            m = Integer.parseInt(in.nextLine());
            System.out.print("n: ");
            n = Integer.parseInt(in.nextLine());
            double[][] A = new double[m][n+1];
            for(i = 0; i < m; i++) {
                for (j = 0; j < n+1; j++) {
                    if (j != n) {
                        System.out.print("Koefisien a"+(i+1)+(j+1)+": ");
                    }
                    else {
                        System.out.print("Konstanta b"+(i+1)+": ");
                    }
                    A[i][j] = Double.parseDouble(in.nextLine());
                }
            }
            mat = new Matriks(A);
        }
        else if (choiceInput == 2) {
            JFrame frame = new JFrame();
            String path = System.getProperty("user.dir");
            JFileChooser fileChooser = new JFileChooser(Paths.get(path).getParent().toString()+"\\test");
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                mat = new Matriks(inputFile);
                frame.dispose();
            }
            else {
                System.out.println("No file selected");
                System.exit(0);
            }
        }
        else {
            System.out.println("Input tidak valid");
            System.exit(0);
        }
        
    }

    private void printSols() {
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Output ke layar");
        System.out.println("2. Output ke file"); 
        System.out.print("Pilihan: ");
        int choiceOutput;
        choiceOutput = Integer.parseInt(in.nextLine());
        switch (choiceOutput) {
            case 1:
                this.toFile = false;
                break;
            case 2:
                this.toFile = true;
                break;
            default:
                System.out.println("Input tidak valid");
                System.exit(0);
        }
        if (this.toFile) {
            JFrame frame = new JFrame();
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    for (int i = 0; i < solutions.length; i++) {
                        writer.write("X" + (i+1) + " = " + solutions[i]+"\n");
                    }
                    writer.close();
                    frame.dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("No file selected");
                System.exit(0);
            }
        }
        else {
            for (int i = 0; i < solutions.length; i++) {
                System.out.println("X" + (i+1) + " = " + solutions[i]);
            }
        }
    }

    private void printParametrik() {
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Output ke layar");
        System.out.println("2. Output ke file"); 
        System.out.print("Pilihan: ");
        int choiceOutput;
        choiceOutput = Integer.parseInt(in.nextLine());
        switch (choiceOutput) {
            case 1:
                this.toFile = false;
                break;
            case 2:
                this.toFile = true;
                break;
            default:
                System.out.println("Input tidak valid");
                System.exit(0);
        }
        String[] variables = {"r", "s", "t", "u", "v", "w"};
        String sols[] = new String[this.mat.N-1];
        Arrays.fill(sols, "");
        int k = 0;
        boolean leadCoef[] = new boolean[this.mat.N-1];
        for (int j = 0; j < this.mat.N-1; j++) {
            boolean lead = false;
            for (int i = 0; i < this.mat.M; i++) {
                if (j == this.mat.leadingCoef(i)) {
                    lead = true;
                    break;
                }
            }
            if (!lead) {
                sols[j] = variables[k];
                k++;
            }
        }
        for (int i = 0; i < this.mat.M; i++) {
            if (this.mat.leadingCoef(i) < this.mat.N) {
                sols[this.mat.leadingCoef(i)] = Double.toString(this.mat.Mat[i][this.mat.N-1]);
                if (this.mat.Mat[i][this.mat.N-1] == 0) {
                    sols[this.mat.leadingCoef(i)] = "";
                }
                for (int j = this.mat.leadingCoef(i)+1; j < this.mat.N-1; j++) {
                    if (this.mat.Mat[i][j] != 0) {
                        String s = Double.toString(-1*this.mat.Mat[i][j]);
                        if (Math.abs(this.mat.Mat[i][j]) == 1) {
                            s = this.mat.Mat[i][j] == 1 ? "-" : "";
                        }
                        if (this.mat.Mat[i][j] <= 0 && sols[this.mat.leadingCoef(i)] != "") {
                            s = " + "+ s;
                        }
                        else if (this.mat.Mat[i][j] > 0 && sols[this.mat.leadingCoef(i)] != "") {
                            s = " - " + Double.toString(this.mat.Mat[i][j]) ;
                        }
                        sols[this.mat.leadingCoef(i)] += s + sols[j];
                    }
                }
            }
        }
        if (this.toFile) {
            JFrame frame = new JFrame();
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    for (int i = 0; i < sols.length; i++) {
                        writer.write("X"+(i+1)+" = " + sols[i]+"\n");
                    }
                    writer.close();
                    frame.dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("No file selected");
                System.exit(0);
            }
        }
        else {
            for (int i = 0; i < sols.length; i++) {
                System.out.println("X"+(i+1)+" = " + sols[i]);
            }
        }
    }  

    public void cramer() {
        if (this.mat.M == this.mat.N-1) {
            double[][] tempA = new double[this.mat.M][this.mat.N-1];
            double[] b = new double[this.mat.M];
            int i, j;   
            for (i = 0; i < tempA.length; i++) {
                for (j = 0; j < tempA[0].length; j++) {
                    tempA[i][j] = this.mat.Mat[i][j];
                }
            }
            Matriks A = new Matriks(tempA);
            for (i = 0; i < b.length; i++) {
                b[i] = this.mat.Mat[i][this.mat.N-1];
            }
            double detA = A.detCofactor();
            if (detA != 0) {
                this.solutions = new double[this.mat.M];
                for (i = 0; i < this.mat.M; i++) {
                    Matriks Ai = A.clone(); 
                    for (int k = 0; k < b.length; k++) {    
                        Ai.Mat[k][i] = b[k];
                    }
                    double detAi = Ai.detCofactor();
                    this.solutions[i] = detAi / detA;
                }
                printSols();
            }
            else {
                System.out.println("Determinan matriks 0, solusi tidak dapat dicari dengan metode Cramer");
            }   
        }
        else {
            System.out.println("Banyak peubah dan persamaan SPL berbeda, solusi tidak dapat dicari dengan metode Cramer");
        }
    }  

    public void gauss() {
        this.mat.gauss();
        boolean hasSolution = false;
        int k = 0;
        while (!hasSolution && k < this.mat.N-1) {
            if (this.mat.Mat[this.mat.M-1][k] != 0) {
                hasSolution = true;
            }
            k++;
        }
        if (!hasSolution) {
            if (this.mat.Mat[this.mat.M-1][this.mat.N-1] == 0) {
                hasSolution = true;
            }
        }

        if (hasSolution) {
            boolean parametric = false;
            for (int j = 0; j < this.mat.N-1; j++) {
                boolean jLead = false;
                int i = 0;
                while (!jLead && i < this.mat.M) {
                    if (this.mat.leadingCoef(i) == j) {
                        jLead = true;
                    }
                    i++;
                }
                if (!jLead) {
                    parametric = true;
                    break;
                }
            }

            if (parametric) {
                this.mat.gaussJordan();
                printParametrik();
            }
            else {
                this.solutions = new double[this.mat.N-1];
                for (int i = this.mat.N-2; i >= 0; i--) {
                    this.solutions[i] = this.mat.Mat[i][this.mat.N-1];

                    for (int j = i+1; j < this.mat.N-1; j++) {
                        this.solutions[i] -= this.mat.Mat[i][j]*this.solutions[j];
                    }
                }
                printSols();
            }
        }
        else {
            System.out.println("SPL tidak memiliki solusi");
        }
    }

    public void gaussJordan() {
        this.mat.gaussJordan();
        boolean hasSolution = false;
        int jsol = 0;
        while (!hasSolution && jsol < this.mat.N-1) {
            if (this.mat.Mat[this.mat.M-1][jsol] != 0) {
                hasSolution = true;
            }
            jsol++;
        }
        if (!hasSolution) {
            if (this.mat.Mat[this.mat.M-1][this.mat.N-1] == 0) {
                hasSolution = true;
            }
        }

        if (hasSolution) {
            boolean parametric = false;
            for (int j = 0; j < this.mat.N-1; j++) {
                boolean jLead = false;
                int i = 0;
                while (!jLead && i < this.mat.M) {
                    if (this.mat.leadingCoef(i) == j) {
                        jLead = true;
                    }
                    i++;
                }
                if (!jLead) {
                    parametric = true;
                    break;
                }
            }

            if (parametric) {
                printParametrik();
            }
            else {
                this.solutions = new double[this.mat.N-1];
                for (int i = 0; i < this.mat.N-1; i++) {
                    this.solutions[i] = this.mat.Mat[i][this.mat.N-1];
                }
                printSols();
            }
        }
        else {
            System.out.println("SPL tidak memiliki solusi");
        }
    }

    public void inverse(){
        if(this.mat.M == this.mat.N-1){
            double[][] tempA = new double[this.mat.M][this.mat.N-1];
            double[] b = new double[this.mat.M];
            int i, j;
            for (i = 0; i < tempA.length; i++) {
                for (j = 0; j < tempA[0].length; j++) {
                    tempA[i][j] = this.mat.Mat[i][j];
                }
            }
            Matriks A = new Matriks(tempA);
            for (i = 0; i < b.length; i++) {
                b[i] = this.mat.Mat[i][this.mat.N-1];
            }
            this.solutions = new double[this.mat.M]; // hasil perkalian A inverse dengan b
            Matriks AInverse = A.inverseGaussJordan();

            for (i = 0; i < this.solutions.length; i++){
                this.solutions[i] = 0;
            }
            for(i = 0; i < this.mat.M; i++){
                for(j = 0; j < this.mat.M; j++){
                    this.solutions[i] += (AInverse.Mat[i][j] * b[j]);
                }
            }
            printSols();
        }
        else{
            System.out.println("Banyak peubah dan banyak persamaan SPL berbeda, solusi tidak dapat dicari dengan metode inverse");
        }
    }  
}
