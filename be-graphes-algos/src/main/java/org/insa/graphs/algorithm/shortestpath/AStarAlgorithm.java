package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.GraphStatistics;
import org.insa.graphs.model.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected Label createLabel(Node node) {
        double distance = this.getInputData().getDestination().getPoint().distanceTo(node.getPoint());
        double cost;
        if(this.data.getMode() == AbstractInputData.Mode.LENGTH) {
            cost = distance;
        } else {
            // (this.data.getMode() == AbstractInputData.Mode.TIME)
            double max_speed;
            if(this.data.getMaximumSpeed() != GraphStatistics.NO_MAXIMUM_SPEED) {
                max_speed = this.data.getMaximumSpeed();
            } else {
                max_speed = this.data.getGraph().getGraphInformation().getMaximumSpeed();
            }
            cost = distance*3.6 / max_speed;
        }

        return new LabelAStar(node,
                cost,
                (float) Double.POSITIVE_INFINITY);
    }
}
