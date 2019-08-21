package com.epam.task1_6.entity;

import java.util.List;
import java.util.Objects;

/**
 * Fields lengthOfEdge and height is calculated inside of
 * class so to save resources processing time in the future.
 * Field upsideDown is used to calculate relation of two figures
 * thar are cut by XY plane.
 */
public class Tetrahedron {
    private final Point top ;
    private final Point circle;
    private Double lengthOfEdge = null;
    private Double height = null;
    private final boolean upsideDown;

    public Tetrahedron(Point top, Point circle) {
        this.top = top;
        this.circle = circle;
        this.upsideDown = top.getZ() < circle.getZ();
    }

    public Point getTop() {
        return top;
    }

    public Point getCircle() {
        return circle;
    }

    public double getLengthOfEdge() {
        if(lengthOfEdge == null){
            lengthOfEdge = getHeight()*3/Math.sqrt(6);
        }
        return lengthOfEdge;
    }

    public double getHeight(){
        if(height == null){
            height = Math.sqrt(Math.pow(top.getX()-circle.getX(),2)
                    + Math.pow(top.getY()-circle.getY(),2)
                    + Math.pow(top.getZ()-circle.getZ(),2));
        }
        return height;
    }

    public boolean isUpsideDown() {
        return upsideDown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tetrahedron that = (Tetrahedron) o;
        return  top.equals(top) &&
                circle.equals(circle);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(31*top.getX() + 31*circle.getX());
    }
}
