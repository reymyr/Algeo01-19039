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
            mat = new Matriks(A);  
        }
        else if (choiceInput == 2) {
            JFrame frame = new JFrame();
            String path = System.getProperty("user.dir");
            JFileChooser fileChooser = new JFileChooser(Paths.get(path).getParent().toString()+"\\test");
            if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                File inputFile = fileChooser.getSelectedFile();
                try {
                    int i, j;
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
                    for(i = 0; i < rows; i++) {
                        for (j = 0; j < cols; j++) {
                            A[i][j] = fileScanner.nextDouble();
                        }
                    }
                    fileScanner.close();
    
                    this.mat = new Matriks(cols, cols+1);
                    for (int i = 0; i < mat.M ;i++) {
                        for (int j = 0; j < mat.N-1; j++) {
                            double el = 0;
                            for (int k = 0; k < rows; k++) {
                                el += (j == 0 ? 1 : A[k][j]) * (i == 0 ? 1 : A[k][i]);
                            }
                            this.mat.Mat[i][j] = el;
                        }
                    }
                    this.mat.printMatriks();
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
            this.xk = new double[col];
            for (int i = 0 ; i < col; i++){
                System.out.print("Masukkan xk" + (i + 1) + ": ");
                this.xk[i] = in.nextDouble();
            }
        }
        else {
            System.out.println("Input tidak valid");
            System.exit(0);
        }
        
    }

    public void LRSolve() {
        
    }
}
