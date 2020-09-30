import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.*;

public class Interpolasi {
    Matriks mat;
    double x;
    boolean toFile;

    public Interpolasi() {
        int choiceInput;
        Scanner in = new Scanner(System.in);
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file"); 
        System.out.print("Pilihan: ");
        choiceInput = Integer.parseInt(in.nextLine());
        if (choiceInput == 1) {
            int n;
            System.out.print("n: ");
            n = Integer.parseInt(in.nextLine());
            double[][] A = new double[n+1][2];
            for(int k = 0; k < n+1; k++) {
                System.out.print("x"+k+": ");
                A[k][0] = Double.parseDouble(in.nextLine());
                System.out.print("y"+k+": ");
                A[k][1] = Double.parseDouble(in.nextLine());
            }
            this.mat = new Matriks(n+1, n+2);
            for (int i = 0; i < mat.M ;i++) {
                for (int j = 0; j < mat.N; j++) {
                    if (j != mat.N-1) {
                        this.mat.Mat[i][j] = Math.pow(A[i][0], j);
                    }
                    else {
                        this.mat.Mat[i][j] = A[i][1];
                    }
                }
            }
            System.out.print("Nilai x yang akan ditaksir: ");
            this.x = Double.parseDouble(in.nextLine());
        }
        else if (choiceInput == 2) {
            JFrame frame = new JFrame();
            String path = System.getProperty("user.dir");
            JFileChooser fileChooser = new JFileChooser(Paths.get(path).getParent().toString()+"\\test");
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                try {
                    int rows = 0;
                    Scanner rowScanner = new Scanner(inputFile);
                    while (rowScanner.hasNextLine()) {
                        rows++;
                        rowScanner.nextLine();
                    }
                    rowScanner.close();
    
                    double A[][] = new double[rows][2];
                    Scanner fileScanner = new Scanner(inputFile);
                    for(int i = 0; i < rows; i++) {
                        for (int j = 0; j < 2; j++) {
                            A[i][j] = fileScanner.nextDouble();
                        }
                    }
                    fileScanner.close();
    
                    this.mat = new Matriks(rows, rows+1);
                    for (int i = 0; i < mat.M ;i++) {
                        for (int j = 0; j < mat.N; j++) {
                            if (j != mat.N-1) {
                                this.mat.Mat[i][j] = Math.pow(A[i][0], j);
                            }
                            else {
                                this.mat.Mat[i][j] = A[i][1];
                            }
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
            System.out.print("Nilai x yang akan ditaksir: ");
            this.x = Double.parseDouble(in.nextLine());
        }
        else {
            System.out.println("Input tidak valid");
            System.exit(0);
        }
    }

    public void solve() {
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
                    writer.write("Persamaan polinom:\n");
                    double res = 0;
                    for (int i = 0; i < solutions.length; i++) {
                        res += solutions[i]*Math.pow(this.x, i);
                        writer.write(String.format("%.4f", Math.abs(solutions[i])));
                        if (i > 0) {
                            writer.write("x");
                            if (i > 1) {
                                writer.write("^"+i);
                            }
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
            System.out.println("Persamaan polinom:");
            double res = 0;
            for (int i = 0; i < solutions.length; i++) {
                res += solutions[i]*Math.pow(this.x, i);
                System.out.print(String.format("%.4f", Math.abs(solutions[i])));
                if (i > 0) {
                    System.out.print("x");
                    if (i > 1) {
                        System.out.print("^"+i);
                    }
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
