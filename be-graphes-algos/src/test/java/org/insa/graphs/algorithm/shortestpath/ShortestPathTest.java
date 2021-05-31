package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public abstract class ShortestPathTest {
    @Parameterized.Parameter
    public ShortestPathData parameters;
    protected static String carre_filename = "../Maps/carre.mapgr";
    protected static String insa_filename = "../Maps/insa.mapgr";

    /**
     * Create algorithm from data
     *
     * @param data Data to pass to algorithm
     * @return New algorithm
     */
    public abstract ShortestPathAlgorithm createAlgorithm(ShortestPathData data);

    @Parameterized.Parameters
    public static Collection<ShortestPathData> data() throws IOException {
        BinaryGraphReader reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(carre_filename)));
        Graph graph_carre = reader.read();
        reader.close();
        reader = new BinaryGraphReader(new DataInputStream(new FileInputStream(insa_filename)));
        Graph graph_insa = reader.read();
        reader.close();
        Collection<ShortestPathData> objects = new ArrayList<>();
        for (ArcInspector inspector :
                ArcInspectorFactory.getAllFilters()) {
            for (Node origin :
                    graph_carre.getNodes()) {
                for (Node destination :
                        graph_carre.getNodes()) {
                    objects.add(new ShortestPathData(graph_carre, origin, destination, inspector));
                }
            }

            int i = 0;
            for (Node origin :
                    graph_insa.getNodes()) {
                for (Node destination :
                        graph_insa.getNodes()) {
                    i++;
                    objects.add(new ShortestPathData(graph_insa, origin, destination, inspector));
                }
                if(i > 6000) {
                    break;
                }
            }
        }
        return objects;
    }

    @Test
    public void all() {
        ShortestPathAlgorithm algorithm = this.createAlgorithm(this.parameters);
        BellmanFordAlgorithm bellman_ford = new BellmanFordAlgorithm(this.parameters);

        ShortestPathSolution solution_bellman_ford = bellman_ford.run();
        ShortestPathSolution solution = algorithm.run();
        assertEquals(solution_bellman_ford.isFeasible(), solution.isFeasible());
        if(solution_bellman_ford.isFeasible()) {
            assertNotNull(solution.getPath());
            assertEquals(this.parameters.getCost(solution_bellman_ford.getPath()), this.parameters.getCost(solution.getPath()), 0.001f);
        }
    }
}
