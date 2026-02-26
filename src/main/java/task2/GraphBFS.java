package task2;

import java.util.*;

public class GraphBFS {

    private final Map<Integer, List<Integer>> adjList = new HashMap<>();
    private final Set<Integer> vertices = new HashSet<>();

    public void addEdge(int from, int to) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        vertices.add(from);
        vertices.add(to);
    }

    public void addVertex(int v) {
        vertices.add(v);
    }

    /**
     * Реализация BFS
     * @param start - начальная вершина
     * @return список вершин в порядке обхода
     */
    public List<Integer> bfs(int start) {
        if (!hasVertex(start)) {
            throw new IllegalArgumentException("Вершина " + start + " не существует в графе");
        }
        List<Integer> visitedOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visitedOrder.add(node);

            List<Integer> neighbors = adjList.getOrDefault(node, Collections.emptyList());
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return visitedOrder;
    }

    public boolean hasVertex(int vertex) {
        return vertices.contains(vertex);
    }
}
