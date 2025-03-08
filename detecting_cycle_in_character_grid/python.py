from collections import defaultdict
# Global variables
adj = defaultdict(set)
dir = [(1, 0), (0, 1), (-1, 0), (0, -1)]
def fill(i, j, grid):
   n, m = len(grid), len(grid[0])
   u = i * m + j  # Unique identifier for the cell
   for d in dir:
       a, b = i + d[0], j + d[1]  # Neighbor cell coordinates
       if 0 <= a < n and 0 <= b < m and grid[a][b] == grid[i][j]:
           v = a * m + b  # Unique identifier for the neighbor cell
           adj[u].add(v)  # Add edge to adjacency list



def dfs_cycle(node, par, viss):
   viss[node] = True
   for it in adj[node]:
       if not viss[it]:
           if dfs_cycle(it, node, viss):
               return True
       elif viss[it] and it != par:
           return True
   return False


def contains_cycle(grid):
   n, m = len(grid), len(grid[0])
   adj.clear()  # Clear the adjacency list for each new grid


   # Build the adjacency list
   for i in range(n):
       for j in range(m):
           fill(i, j, grid)


   viss = [False] * (n * m + 1)  # Visited array


   # Check for cycles in each component
   for i in range(n * m):
       if not viss[i] and dfs_cycle(i, -1, viss):
           return True
   return False


def main():
   n = int(input())
   grid = []


   # Read the grid input
   for _ in range(n):
       row = input().strip().split()
       grid.append([char for char in row])


   ans = contains_cycle(grid)
   print("true" if ans else "false")


if __name__ == "__main__":
   main()
