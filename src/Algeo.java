import java.util.Scanner;

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
                System.out.println("Sisten Persamaan Linier\n"
                                    + "1. Metode eliminasi Gauss\n"
                                    + "2. Metode eliminasi Gauss-Jordan\n"
                                    + "3. Metode matriks balikan\n"
                                    + "4. Kaidah Cramer\n");
                int choiceSPL;
                System.out.print("Pilihan: ");
                choiceSPL = Integer.parseInt(in.nextLine());    
                switch (choiceSPL) {
                    case 1:
                
                        break;

                    case 2:
                    
                        break;

                    case 3:
                    
                        break;

                    case 4:
                    
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
