package com.epam.task1_6.action;

import com.epam.task1_6.entity.Axes;
import com.epam.task1_6.entity.Point;
import com.epam.task1_6.entity.Tetrahedron;
import com.epam.task1_6.exceptions.CantBeCalculatedException;
import com.epam.task1_6.exceptions.TetrahedronException;
import com.epam.task1_6.planes.InterfaceForCalculatingRelation;
import com.epam.task1_6.scanner.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Calculator {
    private static final Calculator INSTANCE = new Calculator();
    private Logger logger = LogManager.getLogger(FileReader.class);

    private Calculator(){}

    public static Calculator getInstance(){
       return INSTANCE;
    }

    /**
     * Returns <tt>true</tt> if tetrahedron is parallel to certain axes
     * that is specified with second parameter.
     * @param tetrahedron which is checked on parallel property xD.
     * @param axes is plane to which tetrahedron should or not be parallel.
     * @return <tt>true</tt> if tetrahedron is parallel to axes.
     */
    public boolean isParallelToAxes(Tetrahedron tetrahedron, Axes axes){
            if (axes == Axes.XY) {
                return tetrahedron.getTop().getX() == tetrahedron.getCircle().getX() &&
                        tetrahedron.getTop().getY() == tetrahedron.getCircle().getY();
            } else if (axes == Axes.XZ) {
                return tetrahedron.getTop().getX() == tetrahedron.getCircle().getX() &&
                        tetrahedron.getTop().getZ() == tetrahedron.getCircle().getZ();
            } else {
                return tetrahedron.getTop().getY() == tetrahedron.getCircle().getY() &&
                        tetrahedron.getTop().getZ() == tetrahedron.getCircle().getZ();
            }
    }

    /**
     * Returns <tt>double</tt> area of tetrahedron.
     * @param tetrahedron for calculations.
     * @return <tt>double</tt> area of tetrahedron.
     */
    public double findAreaOfTetrahedron(Tetrahedron tetrahedron) throws IllegalArgumentException{
        return tetrahedron.getLengthOfEdge()*tetrahedron.getLengthOfEdge()*Math.sqrt(3);
    }

    /**
     * Returns <tt>double</tt> volume of tetrahedron.
     * @param tetrahedron for calculations.
     * @return <tt>double</tt> volume of tetrahedron.
     */
    public double findVolumeOfTetrahedron(Tetrahedron tetrahedron) throws IllegalArgumentException{
        return Math.pow(tetrahedron.getLengthOfEdge(), 3)*Math.sqrt(2)/12;
    }

    /**
     * Returns <tt>double</tt> relation of two parts after cutting tetrahedron
     * with plane that depends on parameter predicate. Tetrahedron should be
     * parallel to specific Axis that is specified by parameter predicate.
     * @param tetrahedron for calculations.
     * @param predicate specifying plane and returns volume of cutted part.
     * @return <tt>double</tt> relation of two parts after operation.
     * @throws IllegalArgumentException if tetrahedron or predicate is null.
     * @throws UnsupportedOperationException tetrahedron might be not parallel to axis as it is required for simple calculations.
     */
    public double findRelationOfVolumesOfFigures(Tetrahedron tetrahedron, InterfaceForCalculatingRelation predicate) throws IllegalArgumentException, UnsupportedOperationException {
        if(tetrahedron==null||predicate==null){
            logger.error("Null in findRelationOfVolumesOfFigures!!");
            throw new IllegalArgumentException("Null in findRelationOfVolumesOfFigures!");
        }
        if(predicate.canBeCalculated(tetrahedron)) {
            if (predicate.isTouchingTetrahedron(tetrahedron)) {
                double volumeOfPart = predicate.getVolumeOfPart(tetrahedron);
                double d = Calculator.getInstance().findVolumeOfTetrahedron(tetrahedron);
                double s = (Calculator.getInstance().findVolumeOfTetrahedron(tetrahedron) - volumeOfPart);
                return volumeOfPart / s;
            } else {
                return 0;
            }
        }else{
            logger.error("Cant calculate relation because tetrahedron is not parallel to certain Axis");
            throw new UnsupportedOperationException("Cant calculate relation because tetrahedron is not parallel to certain Axis");
        }
    }

    public boolean isBaseOnAxes(Tetrahedron tetrahedron, Axes axes){
        if(isParallelToAxes(tetrahedron, axes)){
            return false;
        }
        if (axes == Axes.XY) {
            return tetrahedron.getCircle().getZ()==0;
        } else if (axes == Axes.XZ) {
            return tetrahedron.getCircle().getY()==0;
        } else {
            return tetrahedron.getCircle().getX()==0;
        }
    }

}


