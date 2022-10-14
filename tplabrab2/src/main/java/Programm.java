
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kseni
 */
public class Programm {
    
    //static int N, NZ;
    //Matrix mtx = new Matrix();
    
    public static void main(String[] args) {
        int N, NZ;
        //int mas[];
        Scanner in = new Scanner(System.in);
        System.out.println("Размерность матрицы: ");
        N = in.nextInt();
        int mas[] = new int[N];
        double newMatrix[][] = new double[N][N];
        System.out.println(""+N);
        System.out.println("Кол-во ненулевых элементов "
                + "верхнего или нижнего треугольника: ");
        NZ = in.nextInt();
        System.out.println(""+NZ);
        Matrix mtx = new Matrix();
        mtx.initMatrix(N, NZ);
        mtx.pushMatrix(N, NZ, mtx, mas);
        System.out.println("AD");
        for (int o = 0; o < mtx.AD.length; o++){
            System.out.print(" " + mtx.AD[o]);
        }
        System.out.println("\nAU");
        for (int o = 0; o < mtx.AU.length; o++){
            System.out.print(" " + mtx.AU[o]);
        }
        System.out.println("\nAL");
        for (int o = 0; o < mtx.AL.length; o++){
            System.out.print(" " + mtx.AL[o]);
        }
        System.out.println("\nLJ");
        for (int o = 0; o < mtx.LJ.length; o++){
            System.out.print(" " + mtx.LJ[o]);
        }
        System.out.println("\nLI");
        for (int o = 0; o < mtx.LI.length; o++){
            System.out.print(" " + mtx.LI[o]);
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Матрица:");
        mtx.printMatrix(N, mtx, mas);
        mtx.buildMatrix(N, mtx, mas, newMatrix);
        System.out.println("\nОпределитель равен: " + mtx.getOpred(newMatrix, N));
    }
}
