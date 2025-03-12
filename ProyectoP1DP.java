import java.util.*;
import java.io.*;

public class ProyectoP1DP {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        for (int tcase = 0; tcase < t; tcase++) {
            int n = sc.nextInt();
            int j = sc.nextInt();
            int m = sc.nextInt();
            int[] pesos = new int[n];
            for (int i = 0; i < n; i++) {
                pesos[i] = sc.nextInt();
            }
            int ans = solve(n, j, m, pesos);
            System.out.println(ans);
        }
        sc.close();
    }
    
    public static int solve(int n, int j, int m, int[] pesos) {
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[j+1][m+1];
        
        for (int k = 0; k <= j; k++) {
            Arrays.fill(dp[k], INF);
        }
        dp[0][0] = 0;
        
        // Iteramos sobre cada jugador en el orden original
        for (int i = 0; i < n; i++) {
            // Iteramos en orden inverso para evitar reutilizar el mismo jugador
            for (int k = j - 1; k >= 0; k--) {
                int costAdd = i - k;
                // Ajustamos el rango de c para que newCost = c + costAdd esté en [0, m]
                int start = Math.max(0, -costAdd);
                int end = Math.min(m, m - costAdd);
                for (int c = start; c <= end; c++) {
                    if (dp[k][c] != INF) {
                        int newCost = c + costAdd;
                        // newCost siempre estará entre 0 y m
                        dp[k+1][newCost] = Math.min(dp[k+1][newCost], dp[k][c] + pesos[i]);
                    }
                }
            }
        }
        
        int ans = INF;
        for (int c = 0; c <= m; c++) {
            ans = Math.min(ans, dp[j][c]);
        }
        return ans;
    }
}



