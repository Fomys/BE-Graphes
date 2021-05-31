package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelAStar extends Label {
    private double estimated_cost;
    public LabelAStar(Node sommet) {
        super(sommet);
    }

    public LabelAStar(Node sommet, double estimated_cost, double cost) {
        super(sommet, cost);
        this.estimated_cost = estimated_cost;
    }

    @Override
    public double getEstimatedCost() {return this.estimated_cost;}

    @Override
    public int compareTo(Label label) {
        int value = Double.compare(this.getTotalCost(), label.getTotalCost());
        if (value == 0) {
            return Double.compare(this.getEstimatedCost(), label.getEstimatedCost());
        }else {
            return value;
        }
    }
}
