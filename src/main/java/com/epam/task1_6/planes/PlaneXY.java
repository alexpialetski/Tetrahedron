package com.epam.task1_6.planes;

import com.epam.task1_6.action.Calculator;
import com.epam.task1_6.entity.Axes;
import com.epam.task1_6.entity.Tetrahedron;

/**
 * PlaneXY is class that acts as plane this is parallel to XY.
 * As far as x and y are constant variable indent represents coordinate Z.
 */
public class PlaneXY implements InterfaceForCalculatingRelation {
    private double indent=0;

    public PlaneXY(double indent) {
        this.indent = indent;
    }

    @Override
    public boolean isTouchingTetrahedron(Tetrahedron tetrahedron) {
        if(!tetrahedron.isUpsideDown()){
           return indent > tetrahedron.getCircle().getZ() &&
                    indent < tetrahedron.getTop().getZ();
        }else{
            return indent < tetrahedron.getCircle().getZ() &&
                    indent > tetrahedron.getTop().getZ();
        }
    }

    @Override
    public boolean canBeCalculated(Tetrahedron tetrahedron) {
        return Calculator.getInstance()
                .isParallelToAxes(tetrahedron, Axes.XY);
    }

    /**
     * Returns <tt>double</tt> volume of one part.
     * Variable height represents length of small
     * line that is used to calculate volume of part.
     * @return <tt>double</tt> volume of one part.
     */
    @Override
    public double getVolumeOfPart(Tetrahedron tetrahedron) {
        double height;
        if(!tetrahedron.isUpsideDown()){
            height = tetrahedron.getTop().getZ() - indent;
        }else{
            height = tetrahedron.getCircle().getZ() - indent;
        }
        return Math.pow(height,3)*Math.sqrt(3)/8;
    }
}
