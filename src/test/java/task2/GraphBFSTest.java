package task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GraphBFSTest {

    @Test
    @DisplayName("BFS: простой связный граф 1→2,1→3,2→4,3→4")
    void testBFS_simpleGraph() {
        GraphBFS graph = new GraphBFS();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        List<Integer> expectedOrder = Arrays.asList(1, 2, 3, 4);

        List<Integer> actualOrder = graph.bfs(1);

        assertEquals(expectedOrder, actualOrder, "Все вершины посещены");
    }

    @Test
    @DisplayName("BFS: граф с двумя компонентами связности")
    void testBFS_disconnectedGraph() {
        GraphBFS graph = new GraphBFS();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5); //отдельная компонента

        List<Integer> expectedOrder = Arrays.asList(1, 2, 3);
        List<Integer> actualOrder = graph.bfs(1);
        assertEquals(expectedOrder, actualOrder, "Посещены вершины только из первой компоненты связности");

        List<Integer> expectedOrder2 = Arrays.asList(4, 5);
        List<Integer> actualOrder2 = graph.bfs(4);
        assertEquals(expectedOrder2, actualOrder2, "Посещены вершины только из первой компоненты связности");
    }

    @Test
    @DisplayName("BFS: циклический граф")
    void testBFS_cycleGraph() {
        GraphBFS graph = new GraphBFS();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1); //цикл

        List<Integer> expectedOrder = Arrays.asList(1, 2, 3);
        List<Integer> actualOrder = graph.bfs(1);

        assertEquals(expectedOrder, actualOrder, "BFS в циклическом графе завершается");
    }

    @Test
    @DisplayName("BFS: Граф без ребер")
    void testBFS_noEdgesGraph() {
        GraphBFS graph = new GraphBFS();
        graph.addVertex(1);

        List<Integer> expectedOrder = Arrays.asList(1);

        List<Integer> actualOrder = graph.bfs(1);

        assertEquals(expectedOrder, actualOrder, "Все вершины посещены");
    }

    @Test
    @DisplayName("BFS: пустой граф")
    void testBFS_emptyGraph() {
        GraphBFS graph = new GraphBFS();
        assertThrows(IllegalArgumentException.class, () -> {
            graph.bfs(1);
        }, "Для несуществующей вершины будет выброшено исключение");
    }

    @Test
    @DisplayName("BFS: при обходе графа вершины обходятся по уровням")
    void testBfsWithBranching() {
        GraphBFS graph = new GraphBFS();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        List<Integer> result = graph.bfs(1);

        assertEquals(1, result.get(0));
        assertTrue(result.get(1) == 2 || result.get(1) == 3);
        assertTrue(result.get(2) == 2 || result.get(2) == 3);
        assertEquals(4, result.get(3));
    }

    @Test
    @DisplayName("BFS: обход графа корректно обрабатывает дублирующиеся ребра")
    void testBfsWithMultipleEdgesToSameVertex() {
        GraphBFS graph = new GraphBFS();
        graph.addEdge(1, 2);
        graph.addEdge(1, 2);

        List<Integer> result = graph.bfs(1);

        assertEquals(List.of(1, 2), result);
    }


}