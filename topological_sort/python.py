from collections import deque
class Solution:
    def eventualSafeNodes(self, graph):
        adj = [[] for _ in range(len(graph))]
        for i in range(len(graph)):
            for j in graph[i]:
                adj[j].append(i)

        indegree = [0] * len(adj)
        for i in range(len(adj)):
            for j in adj[i]:
                indegree[j] += 1

        queue = deque()
        for i in range(len(indegree)):
            if indegree[i] == 0:
                queue.append(i)

        ans = []
        while queue:
            front = queue.popleft()
            for neighbor in adj[front]:
                indegree[neighbor] -= 1
                if indegree[neighbor] == 0:
                    queue.append(neighbor)

        for i in range(len(indegree)):
            if indegree[i] == 0:
                ans.append(i)

        return ans

if __name__ == "__main__":
    n = int(input())
    mat = []
    for _ in range(n):
        row = list(map(int, input().split()))
        mat.append(row)

    graph = []
    for i in range(n):
        d = []
        for j in range(n):
            if mat[i][j]:
                d.append(j)
        graph.append(d)

    solution = Solution()
    ans = solution.eventualSafeNodes(graph)
    print(*ans)
