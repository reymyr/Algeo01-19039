import java.util.Scanner;
import java.io.File;
import java.io.FileWriter; 
import java.io.IOException;
import java.nio.file.Paths;
import javax.swing.*;

public class InverseSolver {
    Matriks mat;
    Matriks inv;
    boolean toFile;

    public InverseSolver() {
        int choiceInput, n;
        Scanner in = new Scanner(System.in);
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file"); 
        System.out.print("Pilihan: ");
        choiceInput = Integer.parseInt(in.nextLine());
        if (choiceInput == 1) {
            System.out.print("n: ");
            n = Integer.parseInt(in.nextLine());
            mat = new Matriks(n, n);
            mat.inputMatriks();
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

    private void printInv() {
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
                    writer.write("Matriks balikan:\n");
                    int i, j;
                    for (i = 0; i < inv.M; i++) {
                        for (j = 0; j < inv.N; j++) {
                            writer.write(String.format("%.2f", inv.Mat[i][j]) + " ");
                        }
                        writer.write("\n");
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
            System.out.println("Matriks balikan:");
            inv.printMatriks();
        }
    }

    public void solveCofactor() {
        if (this.mat.detCofactor() == 0 || this.mat.M != this.mat.N){
            System.out.println("Matriks tidak memiliki balikan");
        }
        else {
            this.inv = this.mat.inverseCofactor();
            printInv();
        }
    }

    public void solveGaussJordan() {
        if (this.mat.detCofactor() == 0 || this.mat.M != this.mat.N){
            System.out.println("Matriks tidak memiliki balikan");
        }
        else {
            this.inv = this.mat.inverseGaussJordan();
            printInv();
        }
    }
}
