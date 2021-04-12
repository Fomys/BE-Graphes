package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.algorithm.AbstractSolution;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.PriorityQueue;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        notifyOriginProcessed(data.getOrigin());

        BinaryHeap<Label> heap = new BinaryHeap<>();
        HashMap<Node, Label> labels = new HashMap<>();

        for (Node node :
                data.getGraph().getNodes()) {
            Label label = new Label(node);
            labels.put(node, label);
        }

        heap.insert(labels.get(data.getOrigin()));
        heap.findMin().setCost(0);
        AbstractInputData.Mode mode = data.getMode();

        while (!heap.isEmpty()) {
            Label nearest = heap.deleteMin();
            nearest.setFinished(true);
            notifyNodeMarked(nearest.getNode());
            if (nearest.getNode() == data.getDestination()) {
                break;
            }
            System.out.println(nearest.toString());
            for (Arc arc :
                    nearest.getNode().getSuccessors()) {
                System.out.println(arc.toString());
                notifyNodeReached(arc.getDestination());
                Label dest_label = labels.get(arc.getDestination());
                if (!dest_label.isFinished()) {
                    if (dest_label.getCost() > nearest.getCost() + (mode == AbstractInputData.Mode.LENGTH ? arc.getLength() : arc.getMinimumTravelTime())) {
                        dest_label.setCost((float) (nearest.getCost() + (mode == AbstractInputData.Mode.LENGTH ? arc.getLength() : arc.getMinimumTravelTime())));
                        if(dest_label.getArc() == null) {
                            heap.insert(dest_label);
                        }
                        dest_label.setArc(arc);
                    }
                }
            }
        }

        // Création de la liste des arcs
        Label dest = labels.get(data.getDestination());
        if(dest.getArc() == null) {
            return new ShortestPathSolution(data, AbstractSolution.Status.INFEASIBLE, null);
        }
        ArrayList<Arc> arcs = new ArrayList<>();
        arcs.add(0, dest.getArc());

        System.out.println(arcs.get(0));
        while(arcs.get(0).getOrigin() != data.getOrigin()) {
            arcs.add(0, labels.get(arcs.get(0).getOrigin()).getArc());
        }
        solution = new ShortestPathSolution(data, AbstractSolution.Status.OPTIMAL, new Path(data.getGraph(), arcs));
        return solution;
    }

}
