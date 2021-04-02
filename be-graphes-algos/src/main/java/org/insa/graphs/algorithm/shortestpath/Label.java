package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
    private final Node sommet;
    private Arc arc;

    private float cost;
    private boolean finished;

    public Label(Node sommet) {
        this.sommet = sommet;
        this.cost = (float) Double.POSITIVE_INFINITY;
        this.arc = null;
        this.finished = false;
    }

    public Label(Node sommet, float cost) {
        this.sommet = sommet;
        this.cost = cost;
        this.arc = null;
        this.finished = false;
    }

    public float getCost() {
        return this.cost;
    }

    @Override
    public int compareTo(Label label) {
        return this.cost - label.getCost() > 0 ? 1 : -1;
    }

    public Node getNode() {
        return this.sommet;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setArc(Arc arc) {
        this.arc = arc;
    }

    public Arc getArc() {
        return this.arc;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
