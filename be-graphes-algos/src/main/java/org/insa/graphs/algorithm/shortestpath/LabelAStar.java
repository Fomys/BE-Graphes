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
    public double getTotalCost() {
        return super.getCost() + this.estimated_cost;
    }
}
