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
            int[] weight = new int[n];
            for (int i = 0; i < n; i++) {
                weight[i] = sc.nextInt();
            }
            int ans = solve(n, j, m, weight);
            System.out.println(ans);
        }
        sc.close();
    }
    
    public static int solve(int n, int j, int m, int[] weight) {
        int INF = Integer.MAX_VALUE / 2;
        int[][] dp = new int[j+1][m+1];
        
        for (int k = 0; k <= j; k++) {
            Arrays.fill(dp[k], INF);
        }

        // for (int k = 0; k <= j; k++) {
        //     for (int m = 0; m < dp[k].length; m++) {
        //         dp[k][m] = INF;
        //     }
        // }
        
        dp[0][0] = 0;
        
        
        for (int i = 0; i < n; i++) {
            
            for (int k = j - 1; k >= 0; k--) {
                int costAdd = i - k;
                
                int start = Math.max(0, -costAdd);
                int end = Math.min(m, m - costAdd);
                for (int c = start; c <= end; c++) {
                    if (dp[k][c] != INF) {
                        int newCost = c + costAdd;
                        
                        dp[k+1][newCost] = Math.min(dp[k+1][newCost], dp[k][c] + weight[i]);
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



