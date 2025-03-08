import java.util.*;
public class Solution {
   static Map<Integer, Set<Integer>> adj = new HashMap<>();
   static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


   static void fill(int i, int j, char[][] grid) {
       int n = grid.length, m = grid[0].length;
       int u = i * m + j;


       for (int ind = 0; ind < 4; ind++) {
           int a = i + dir[ind][0];
           int b = j + dir[ind][1];


           if (a >= 0 && b >= 0 && a < n && b < m && grid[a][b] == grid[i][j]) {
               int v = a * m + b;
               adj.computeIfAbsent(u, k -> new HashSet<>()).add(v);
           }
       }
   }


   static boolean dfsCycle(int node, int par, boolean[] viss) {
       viss[node] = true;


       for (int it : adj.getOrDefault(node, new HashSet<>())) {
           if (!viss[it]) {
               if (dfsCycle(it, node, viss)) return true;
           } else if (viss[it] && it != par) return true;
       }
       return false;
   }

   static boolean containsCycle(char[][] grid) {
       int n = grid.length, m = grid[0].length;
       adj.clear();


       for (int i = 0; i < n; i++) {
           for (int j = 0; j < m; j++) {
               fill(i, j, grid);
           }
       }


       boolean[] viss = new boolean[n * m + 1];
       Arrays.fill(viss, false);


       for (int i = 0; i < n * m; i++) {
           if (!viss[i] && dfsCycle(i, -1, viss)) return true;
       }
       return false;
   }


   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       char[][] grid = new char[n][n];


       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               grid[i][j] = sc.next().charAt(0);
           }
       }


       boolean ans = containsCycle(grid);
       System.out.println(ans ? "true" : "false");
   }
}
