package org.insa.graphs.algorithm.utils;

import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DijkstraAlgorithmTest {
    private static Graph graph;

    @BeforeClass
    public static void initAll() throws IOException {
        final String mapName = "../Maps/centre.mapgr";

        // Create a graph reader.
        final GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
        graph = reader.read();
    }

    @Test
    public void testEmptyPath() {
        Node origin = graph.getNodes().get(0);
        Node destination = graph.getNodes().get(0);
        ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(data);

        ShortestPathSolution solution = dijkstraAlgorithm.run();

        assertEquals(solution.getPath().getOrigin() , origin);
        assertEquals(solution.getPath().getDestination() , destination);
        assertTrue(solution.getPath().isValid());
    }

    @Test
    public void testAllRoad() {
        Node origin = graph.getNodes().get(0);
        Node destination = graph.getNodes().get(30);
        ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(data);

        ShortestPathSolution solution = dijkstraAlgorithm.run();

        assertEquals(solution.getPath().getOrigin() , origin);
        assertEquals(solution.getPath().getDestination() , destination);
        assertTrue(solution.getPath().isValid());
    }
}
