import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.*;

public class LinearRegression {
    Matriks mat;
    double[] xk;
    boolean toFile;

    public LinearRegression() {
        int choiceInput;
        Scanner in = new Scanner(System.in);
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file");
        System.out.print("Choice (1/2): ");
        choiceInput = Integer.parseInt(in.nextLine());
        if (choiceInput == 1){
            int n, col;
            System.out.print("n (jumlah peubah x): ");
            n = in.nextInt();
            System.out.print("k: ");
            col = in.nextInt();
            this.xk = new double[col];
            double[][] A = new double[n][col+1];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < col+1; j++){
                    if (j != col){
                        System.out.print("Masukkan x"+ (j + 1) + (i + 1) + ": ");
                    }
                    else{
                        System.out.print("Masukkan y" + (i + 1) + ": ");
                    }
                    A[i][j] = in.nextDouble();
                }
            }
            this.mat = new Matriks(col+1, col+2);
            System.out.println(mat.M + " "+mat.N);
            for (int i = 0; i < mat.M ;i++) {
                for (int j = 0; j < mat.N; j++) {
                    double el = 0;
                    for (int k = 0; k < n; k++) {
                        el += (j == 0 ? 1 : A[k][j-1]) * (i == 0 ? 1 : A[k][i-1]);
                    }
                    this.mat.Mat[i][j] = el;
                }
            }
            this.mat.printMatriks();
        }
        else if (choiceInput == 2) {
            JFrame frame = new JFrame();
            String path = System.getProperty("user.dir");
            JFileChooser fileChooser = new JFileChooser(Paths.get(path).getParent().toString()+"\\test");
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                try {
                    int rows = 0, cols = 0;
                    String row = "";
                    Scanner rowScanner = new Scanner(inputFile);
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
                    double[][] A = new double[rows][cols];
                    Scanner fileScanner = new Scanner(inputFile);
                    for(int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            A[i][j] = fileScanner.nextDouble();
                        }
                    }
                    fileScanner.close();
                    this.xk = new double[cols-1];
                    this.mat = new Matriks(cols, cols+1);
                    for (int i = 0; i < mat.M ;i++) {
                        for (int j = 0; j < mat.N; j++) {
                            double el = 0;
                            for (int k = 0; k < rows; k++) {
                                if (j < mat.N-1) {
                                    el += (j == 0 ? 1 : A[k][j]) * (i == 0 ? 1 : A[k][i]);
                                }
                                else {
                                    el += A[k][0] * (i == 0 ? 1 : A[k][i]);
                                }
                            }
                            this.mat.Mat[i][j] = el;
                        }
                    }
                    frame.dispose();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("No file selected");
                System.exit(0);
            }
            
            for (int i = 0 ; i < xk.length; i++){
                System.out.print("Masukkan x" + (i + 1) + ": ");
                this.xk[i] = in.nextDouble();
            }
        }
        else {
            System.out.println("Input tidak valid");
            System.exit(0);
        }
        
    }

    public void LRSolve() {
        this.mat.gauss();
        double[] solutions = new double[this.mat.N-1];
        for (int i = this.mat.N-2; i >= 0; i--) {
            solutions[i] = this.mat.Mat[i][this.mat.N-1];

            for (int j = i+1; j < this.mat.N-1; j++) {
                solutions[i] -= this.mat.Mat[i][j]*solutions[j];
            }
        }
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
                break;
        }
        if (this.toFile) {
            JFrame frame = new JFrame();
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
            if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write("Persamaan regresi:\n");
                    double res = 0;
                    for (int i = 0; i < solutions.length; i++) {
                        res += solutions[i]*(i == 0 ? 1 : xk[i-1]);
                        if (solutions[i] < 0 && i == 0) {
                            writer.write("-");
                        }
                        writer.write(String.format("%.4f", Math.abs(solutions[i])));
                        if (i > 0) {
                            writer.write("x"+i);
                        }
                        if (i < solutions.length-1) {
                            if (solutions[i+1] >= 0) {
                                writer.write(" + ");
                            }
                            else {
                                writer.write(" - ");
                            }
                        }
                    }
                    writer.write("\n\n");
                    writer.write("Nilai taksiran: "+String.format("%.4f", res));
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
            System.out.println("Persamaan regresi:");
            double res = 0;
            for (int i = 0; i < solutions.length; i++) {
                res += solutions[i]*(i == 0 ? 1 : xk[i-1]);
                if (solutions[i] < 0 && i == 0) {
                    System.out.print("-");
                }
                System.out.print(String.format("%.4f", Math.abs(solutions[i])));
                if (i > 0) {
                    System.out.print("x"+i);
                }
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
}
