
import java.util.Arrays;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kseni
 */
public class Matrix {
        int N, NZ;
        int AD[], AU[], AL[], LJ[], LI[];
        
        //функция инициализации матрицы
        void initMatrix(int N, int NZ){
            this.N = N;
            this.NZ = N;
            this.AD = new int[N];
            this.AU = new int[NZ];
            this.AL = new int[NZ];
            this.LI = new int[N];
            this.LJ = new int[NZ];
        }
        
        //функция заполнения матрицы
        public void pushMatrix(int N, int NZ, Matrix mt, int mas[]){
            Scanner input = new Scanner(System.in);
            int i, j, c = 0, el, il = 0, isNewStr = -1, ill =1;
            System.out.println("Введите элементы диагонали");
            for(int k = 0; k < N; k++){
                System.out.println("Элемент с координатами [" + k + "] [" + k
                    + "]");
                mt.AD[k] = input.nextInt();
            }
            System.out.println("Введите элементы матрицы:");
            while (c!= NZ) {
                System.out.println("Координату i = ");
                i = input.nextInt();
                System.out.println("Координату j = ");
                j = input.nextInt();
                System.out.println("[" + i +"] "+ "[" + j+"] ");
                el = input.nextInt();
                if(isNewStr < i){ //если строка новая
                    mt.LI[i]=ill;
                    mas[i]=ill;//записываем какой по счету элемент
                    isNewStr=i; //обозначаем, что строка не новая
                }
                if(i<j){
                    mt.AU[il] = el;
                    mt.LJ[il] = j;
                } else {
                    mt.AU[il] = el;
                    mt.LJ[il] = i;
                }
                System.out.println("Симметричный эллемент [" + j + "] [" 
                        + i + "]: ");
                el = input.nextInt();
                if(i<j){
                    mt.AL[il] = el;
                } else {
                    mt.AL[il] = el;
                }
                c++;
                il++;
                ill++;
            }
            mt.LI[N-1] = ill;
            mas[N-1] = ill;
            for(int g = N-1; g >= 0; g-- ){
                if(mt.LI[g]<0){
                    mt.LI[g] = mt.LI[g+1];
                }
            }
            for(int g = N-1; g >= 0; g-- ){
                if(mas[g]<0){
                    mas[g] = mas[g+1];
                }
            }
            while(mt.isMassLIFull(mt) == false){
                for(int f = N-1; f >= 0; f--){
                    if (mt.LI[f]==0){
                            mt.LI[f] = mt.LI[f+1];
                    }
                }
                if(mt.isMassLIFull(mt)==true){
                    break;
                }
            }
        }
        
        //функция дозаполнения массива LI матрицы
        boolean isMassLIFull(Matrix mt){
            for(int f = 0; f < mt.LI.length; f++){
                if (mt.LI[f]==0){
                    return false;
                }
            }
            return true;
        }

        //функция получения элемента матрицы
        int getElMatrix(int i, int j, Matrix mt, int mas[]){
            int endEl = 0;
            if (i < j){
                int N2 = mt.LI[i+1] - 1; //первый элемент в следующей строке
                for(int k = 0; k < N2; k++){ //смотрим на элементы между ними
                    if (mt.LJ[k]==j && mas[i]!= 0){ 
                    endEl = mt.AU[k];
                    break;
                }
                    
                }
            } else if (i>j){
                int N2 = mt.LI[j+1] - 1;
                for(int k = 0; k < N2; k++){
                    if (mt.LJ[k]==i && mas[j]!= 0){
                        endEl = mt.AL[k];
                        break;
                    }
                }
            } else if(i==j) {
                endEl = mt.AD[i];
            }
            return endEl;
        }

        //функция вывода матрицы
        void printMatrix(int N, Matrix mt, int mas[]){
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    System.out.print("" + mt.getElMatrix( i, j, mt, mas));
                }
                System.out.println("");
            }
        }
        
        //функция конвертирования разреженно структрно-симметричной матрицы в двумерный массив
        void buildMatrix(int N, Matrix mt, int mas[], double newMt[][]){
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    newMt[i][j] = mt.getElMatrix( i, j, mt, mas);
                }
            }
        }
        
        //удаления из матрицы строки и столбца
        void getMatrixWithoutRowAndCol(double newMt[][], int N, int i, int j, double newMt2[][]) {
            int delRow = 0; 
            int delCol = 0; 
            for(int p = 0; p < N-1; p++) {
                if(p == i) {
                    delRow = 1; //помечаем какую строку будем удалять
                }
                delCol = 0; 
                for(int l = 0; l < N-1; l++) {
                    if(l == j) {
                        delCol = 1; //какой столбец бцдем смещать
                    } 
                    newMt2[p][l] = newMt[p + delRow][l + delCol];
                }
            }
        }
        
        double getOpred(double newMt[][], int N){
            double opred = 1;
            int degree = 1; // из формулы определителя
            if(N == 1) { //выход из рекурсии когда нечего считать
                return newMt[0][0];
            }
            else if(N == 2) { //выход из рекурсии тк мы закончили!
                return newMt[0][0]*newMt[1][1] - newMt[0][1]*newMt[1][0];
            }
            else {
                double newMatrix[][] = new double [N-1][N-1];
                for(int i = 0; i < N-1; i++) {
                    newMatrix[i] = new double[N-1];
                }
                //раскладываем по 0-ой строке, цикл бежит по столбцам
                for(int j = 0; j < N; j++) {
                    getMatrixWithoutRowAndCol(newMt, N, 0, j, newMatrix); //удаляем строку и столбец
                    opred = opred + (degree * newMt[0][j] * getOpred(newMatrix, N-1)); //по формуле
                    degree = -degree;
                }
                for(int i = 0; i < N-1; i++) {
                    Arrays.fill(newMatrix, null);
                }
            }
            return opred;
        }
}
