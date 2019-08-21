package com.epam.task1_6.planes;

import com.epam.task1_6.action.Calculator;
import com.epam.task1_6.entity.Axes;
import com.epam.task1_6.entity.Tetrahedron;

/**
 * PlaneYZ is class that acts as plane this is parallel to YZ.
 * As far as y and z are constant variable indent represents coordinate X.
 */
public class PlaneYZ implements InterfaceForCalculatingRelation{
    private double indent=0;

    public PlaneYZ(double indent) {
        this.indent = indent;
    }

    @Override
    public boolean isTouchingTetrahedron(Tetrahedron tetrahedron) {
        return indent > (tetrahedron.getCircle().getX() - tetrahedron.getLengthOfEdge()/2) &&
                indent < (tetrahedron.getCircle().getX() + tetrahedron.getLengthOfEdge()/2);
    }

    @Override
    public boolean canBeCalculated(Tetrahedron tetrahedron) {
        return Calculator.getInstance()
                .isParallelToAxes(tetrahedron, Axes.XY);
    }

    /**
     * Returns <tt>double</tt> volume of one part.
     * Variable length represents length of small
     * line that is used to calculate volume of part.
     * @return <tt>double</tt> volume of one part.
     */
    @Override
    public double getVolumeOfPart(Tetrahedron tetrahedron) {
        double length;
        double circlePoint = tetrahedron.getCircle().getX();
        if(indent <= circlePoint){
            length = indent - (tetrahedron.getCircle().getX() - tetrahedron.getLengthOfEdge()/2);
        }else{
            length = (tetrahedron.getCircle().getX() + tetrahedron.getLengthOfEdge()) - indent;
        }
        return length * Math.pow(tetrahedron.getLengthOfEdge(),2)*Math.sqrt(2)/12;
    }
}
