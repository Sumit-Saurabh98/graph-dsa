import java.util.*;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j : graph[i]) {
                adj.get(j).add(i);
            }
        }

        int[] indegree = new int[graph.length];
        for (List<Integer> neighbors : adj) {
            for (int neighbor : neighbors) {
                indegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int front = queue.poll();
            for (int neighbor : adj.get(front)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                ans.add(i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = scanner.nextInt();
            }
        }

        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            List<Integer> d = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    d.add(j);
                }
            }
            graph[i] = new int[d.size()];
            for(int j = 0; j < d.size(); j++){
                graph[i][j] = d.get(j);
            }
        }

        Solution solution = new Solution();
        List<Integer> ans = solution.eventualSafeNodes(graph);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        scanner.close();
    }
}


