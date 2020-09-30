import java.util.Scanner;

public class Algeo {
    public static void main(String[] args) {
        System.out.println("MENU\n"
                            + "1. Sistem Persamaaan Linier\n"
                            + "2. Determinan\n"
                            + "3. Matriks balikan\n"
                            + "4. Interpolasi Polinom\n"
                            + "5. Regresi linier berganda\n"
                            + "6. Keluar");
        int choice;
        System.out.print("Pilihan: ");
        Scanner in = new Scanner(System.in);
        choice = Integer.parseInt(in.nextLine());
        switch (choice) {
            case 1:
                System.out.println();
                SPLSolver spl = new SPLSolver();
                System.out.println();
                System.out.println("Sisten Persamaan Linier\n"
                                    + "1. Metode eliminasi Gauss\n"
                                    + "2. Metode eliminasi Gauss-Jordan\n"
                                    + "3. Metode matriks balikan\n"
                                    + "4. Kaidah Cramer");
                int choiceSPL;
                System.out.print("Pilihan: ");
                choiceSPL = Integer.parseInt(in.nextLine());    
                switch (choiceSPL) {
                    case 1:
                        spl.gauss();
                        break;

                    case 2:
                        spl.gaussJordan();
                        break;

                    case 3:
                        spl.inverse();
                        break;

                    case 4:
                        spl.cramer();
                        break;
                
                    default:
                        System.out.println("Input pilihan tidak valid");
                        break;
                }
                break;
            case 2:
                System.out.println();
                DeterminanSolver detSolver = new DeterminanSolver();
                System.out.println();
                System.out.println("Determinan\n"
                                    + "1. Metode reduksi baris\n"
                                    + "2. Metode ekspansi kofaktor");
                int choiceDet;
                System.out.print("Pilihan: ");
                choiceDet = Integer.parseInt(in.nextLine());  
                switch (choiceDet){
                    case 1:
                        detSolver.solveRowRed();
                        break;

                    case 2:
                        detSolver.solveCofactor();
                        break;

                    default:
                        System.out.println("Input pilihan tidak valid");
                        break;
                }
                break;
                
            case 3:
                System.out.println();
                InverseSolver invSolver = new InverseSolver();
                System.out.println();
                System.out.println("Matriks balikan\n"
                                    + "1. Metode kofaktor\n"
                                    + "2. Metode Gauss-Jordan");
                int choiceInv;
                System.out.print("Pilihan: ");
                choiceInv = Integer.parseInt(in.nextLine());    
                switch (choiceInv) {
                    case 1:
                        invSolver.solveCofactor();
                        break;

                    case 2:
                        invSolver.solveGaussJordan();
                        break;

                    default:
                        System.out.println("Input pilihan tidak valid");
                        break;
                }
                break;

            case 4:
                System.out.println();
                Interpolasi interp = new Interpolasi();
                interp.solve();
                break;

            case 5:
                System.out.println();
                LinearRegression LR = new LinearRegression();
                LR.LRSolve();
                break;

            case 6:
                System.out.println("Thank you and goodbye! Ciao");
                break;

            default:
                System.out.println("Input pilihan tidak valid");
                break;
        }
        in.close();
    }
}