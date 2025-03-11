import java.util.*;

public class Proyecto_1 {
    
    public static int funcionPrincipal(int[] pesos, int intercambios, int j) {
        int n = pesos.length;
        int[][] dp = new int[intercambios + 1][n];
        
        // Caso base cuando no se tienen intercambios
        System.arraycopy(pesos, 0, dp[0], 0, n);
        
        // Si solo hay un intercambio, solo podemos ver si en la frontera vale la pena intercambiar
        int[] pesosM1 = pesos.clone();
        if (pesos[j - 1] > pesos[j]) {
            swap(pesosM1, j, j - 1, j);
        }
        dp[1] = pesosM1;
        
        // Caso recursivo, m > 1
        for (int m = 2; m <= intercambios; m++) {
            int[] mejorArreglo = dp[m - 1].clone();
            int mejorSum = funcionSumaJPrimeros(mejorArreglo, j);
            
            int[] propios = encontrarMejorSwap(pesos, m, j);
            int sumPropios = funcionSumaJPrimeros(propios, j);
            
            if (sumPropios < mejorSum) {
                mejorArreglo = propios;
                mejorSum = sumPropios;
            }
            
            for (int l = 1; l < m - 1; l++) {
                int[] extremosM = dp[l];
                int[] mejorExtremosM = encontrarMejorSwap(extremosM, m - l, j);
                int sumMejorExtremosM = funcionSumaJPrimeros(mejorExtremosM, j);
                
                if (sumMejorExtremosM < mejorSum) {
                    mejorSum = sumMejorExtremosM;
                    mejorArreglo = mejorExtremosM;
                }
            }
            dp[m] = mejorArreglo;
        }
        
        return funcionSumaJPrimeros(dp[intercambios], j);
    }
    
    public static int funcionSumaJPrimeros(int[] lista, int j) {
        int sum = 0;
        for (int i = 0; i < j; i++) {
            sum += lista[i];
        }
        return sum;
    }
    
    public static void swap(int[] lista, int j, int posMayor, int posMenor) {
        while (posMayor < j - 1) {
            int temp = lista[posMayor];
            lista[posMayor] = lista[posMayor + 1];
            lista[posMayor + 1] = temp;
            posMayor++;
        }
        
        while (posMenor > j) {
            int temp = lista[posMenor];
            lista[posMenor] = lista[posMenor - 1];
            lista[posMenor - 1] = temp;
            posMenor--;
        }
        
        int temp = lista[j - 1];
        lista[j - 1] = lista[j];
        lista[j] = temp;
    }
    
    public static int[] encontrarMejorSwap(int[] lista, int m, int j) {
        int mayorDiferencia = 0;
        int mejorPosicionSwap = -1;
        
        if (m > j) {
            for (int i = 0; i < j; i++) {
                if (i + m < lista.length) {
                    int diferenciaActual = lista[i] - lista[i + m];
                    if (diferenciaActual > mayorDiferencia) {
                        mayorDiferencia = diferenciaActual;
                        mejorPosicionSwap = i;
                    }
                }
            }
        } else {
            for (int i = j - m; i < j; i++) {
                if (i + m < lista.length) {
                    int diferenciaActual = lista[i] - lista[i + m];
                    if (diferenciaActual > mayorDiferencia) {
                        mayorDiferencia = diferenciaActual;
                        mejorPosicionSwap = i;
                    }
                }
            }
        }
        
        if (mejorPosicionSwap > -1) {
            int[] nuevaLista = lista.clone();
            swap(nuevaLista, j, mejorPosicionSwap, mejorPosicionSwap + m);
            return nuevaLista;
        } else {
            return lista.clone();
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ncasos = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < ncasos; i++) {
            String[] input = scanner.nextLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int j = Integer.parseInt(input[1]);
            int m = Integer.parseInt(input[2]);
            int[] pesos = new int[n];
            for (int k = 0; k < n; k++) {
                pesos[k] = Integer.parseInt(input[3 + k]);
            }
            int respuesta = funcionPrincipal(pesos, m, j);
            System.out.println(respuesta);
        }
        scanner.close();
    }
}
