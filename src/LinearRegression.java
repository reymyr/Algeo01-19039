import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LinearRegression {
    Matriks mat;

    public LinearRegression() {
        int choiceInput;
        Scanner in = new Scanner(System.in);
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file");
        System.out.print("Choice (1/2): ");
        choiceInput = Integer.parseInt(in.nextLine());
        if (choiceInput == 1){
            int n, y;
            System.out.print("n: ");
            n = in.nextInt();
            double[] A = new double[n]; // ini gatau mesti array apa matriks
            for (int i = 1; i <= n; i++){
                
            }

        }

    }
}
