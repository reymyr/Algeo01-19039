import java.util.Scanner;
import java.io.File;

public class Algeo {
    public static void main(String[] args) {
        System.out.println("MENU\n"
                            + "1. Sistem Persamaaan Linier\n"
                            + "2. Determinan\n"
                            + "3. Matriks balikan\n"
                            + "4. Interpolasi Polinom\n"
                            + "5. Regresi linier berganda\n"
                            + "6. Keluar\n");
        int choice;
        System.out.print("Pilihan: ");
        Scanner in = new Scanner(System.in);
        choice = Integer.parseInt(in.nextLine());
        switch (choice) {
            case 1:
                int m, n, i, j, choiceInput;
                Matriks mat;
                System.out.println("1. Input dari keyboard");
                System.out.println("2. Input dari file"); 
                System.out.print("Choice: ");
                choiceInput = Integer.parseInt(in.nextLine());
                if (choiceInput == 1) {
                    System.out.print("m: ");
                    m = in.nextInt();
                    System.out.print("n: ");
                    n = in.nextInt();
                    double[][] A = new double[m][n+1];
                    for(i = 0; i < m; i++) {
                        for (j = 0; j < n+1; j++) {
                            if (j != n) {
                                System.out.print("Koefisien a"+(i+1)+(j+1)+": ");
                            }
                            else {
                                System.out.print("Konstanta b"+(i+1)+": ");
                            }
                            A[i][j] = in.nextDouble();
                        }
                    }
                    mat = new Matriks(A);
                }
                else if (choiceInput == 2) {
                    System.out.print("Nama file: ");
                    String filename = in.nextLine();
                    File inputFile = new File("../test/"+filename+".txt");
                    mat = new Matriks(inputFile);
                }
                else {
                    System.out.println("Input tidak valid");
                    break;
                }
                
                System.out.println("\nSisten Persamaan Linier\n"
                                    + "1. Metode eliminasi Gauss\n"
                                    + "2. Metode eliminasi Gauss-Jordan\n"
                                    + "3. Metode matriks balikan\n"
                                    + "4. Kaidah Cramer\n");
                int choiceSPL;
                System.out.print("Pilihan: ");
                choiceSPL = Integer.parseInt(in.nextLine());    
                switch (choiceSPL) {
                    case 1:
                        SPLSolver.gauss(mat);
                        break;

                    case 2:
                        SPLSolver.gaussJordan(mat);
                        break;

                    case 3:
                        SPLSolver.inverse(mat);
                        break;

                    case 4:
                        SPLSolver.cramer(mat);
                        break;
                
                    default:
                        System.out.println("Input pilihan tidak valid");
                        break;
                }
                break;
            case 2:
                System.out.println("Determinan\n"
                                    + "1. Metode reduksi baris\n"
                                    + "2. Metode ekspansi kofaktor\n");
                int choiceDet;
                System.out.print("Pilihan: ");
                choiceDet = Integer.parseInt(in.nextLine());    
                switch (choiceDet) {
                    case 1:

                        break;

                    case 2:

                        break;

                    default:
                        System.out.println("Input pilihan tidak valid");
                        break;
                }
                break;
                
            case 3:
                System.out.println("Matriks balikan\n"
                                    + "1. Metode kofaktor\n"
                                    + "2. Metode Gauss-Jordan\n");
                int choiceInv;
                System.out.print("Pilihan: ");
                choiceInv = Integer.parseInt(in.nextLine());    
                switch (choiceInv) {
                    case 1:

                        break;

                    case 2:

                        break;

                    default:
                        System.out.println("Input pilihan tidak valid");
                        break;
                    }
                break;

            case 4:
                Interpolasi interp = new Interpolasi();
                interp.solve();
                break;

            case 5:
            
                break;

            case 6:
            
                break;

            default:
                System.out.println("Input pilihan tidak valid");
                break;
        }

        in.close();
    }
}
