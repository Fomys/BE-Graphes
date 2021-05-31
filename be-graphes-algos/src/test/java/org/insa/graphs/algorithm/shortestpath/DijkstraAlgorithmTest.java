package org.insa.graphs.algorithm.shortestpath;

public class DijkstraAlgorithmTest extends ShortestPathTest {
    @Override
    public ShortestPathAlgorithm createAlgorithm(ShortestPathData data) {
        return new DijkstraAlgorithm(data);
    }
}
