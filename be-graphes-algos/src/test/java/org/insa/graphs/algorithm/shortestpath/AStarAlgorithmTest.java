package org.insa.graphs.algorithm.shortestpath;

public class AStarAlgorithmTest extends ShortestPathTest {

    @Override
    public ShortestPathAlgorithm createAlgorithm(ShortestPathData data) {
        return new AStarAlgorithm(data);
    }
}
