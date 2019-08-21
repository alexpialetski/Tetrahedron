package com.epam.task1_6.action;

import com.epam.task1_6.entity.Axes;
import com.epam.task1_6.entity.Point;
import com.epam.task1_6.entity.Tetrahedron;
import com.epam.task1_6.exceptions.TetrahedronException;
import com.epam.task1_6.planes.PlaneXY;
import com.epam.task1_6.planes.PlaneYZ;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculatorTest {

    @Test
    public void testIsParallelToAxesXY() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(1,1,1), new Point(1,1,3));

        List<Boolean> actual = new ArrayList<>();
        Collections.addAll(actual, true, false);

        List<Boolean> expected = new ArrayList<>();
        expected.add(Calculator.getInstance().isParallelToAxes(tetrahedron, Axes.XY));
        expected.add(Calculator.getInstance().isParallelToAxes(tetrahedron, Axes.YZ));

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testIsParallelToAxesYZ() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(3,1,1), new Point(1,1,1));

        List<Boolean> actual = new ArrayList<>();
        Collections.addAll(actual, true, false);

        List<Boolean> expected = new ArrayList<>();
        expected.add(Calculator.getInstance().isParallelToAxes(tetrahedron, Axes.YZ));
        expected.add(Calculator.getInstance().isParallelToAxes(tetrahedron, Axes.XY));

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testIsParallelToAxesXZ() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(1,3,1), new Point(1,1,1));

        List<Boolean> actual = new ArrayList<>();
        Collections.addAll(actual, true, false);

        List<Boolean> expected = new ArrayList<>();
        expected.add(Calculator.getInstance().isParallelToAxes(tetrahedron, Axes.XZ));
        expected.add(Calculator.getInstance().isParallelToAxes(tetrahedron, Axes.YZ));

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindAreaOfTetrahedron() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(1,1,1), new Point(1,1,2));

        double expected = 2.598;

        double actual = Calculator.getInstance().findAreaOfTetrahedron(tetrahedron);

        Assert.assertEquals(actual, expected, 0.0001);
    }

    @Test
    public void testFindVolumeOfTetrahedron() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(1,1,1), new Point(1,1,2));

        double expected = 0.2165;

        double actual = Calculator.getInstance().findVolumeOfTetrahedron(tetrahedron);

        Assert.assertEquals(actual, expected, 0.0001);
    }

    @Test
    public void testFindRelationOfVolumesOfFiguresPlaneYZ() throws TetrahedronException {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(1,1,1), new Point(1,1,2));

        List<Double> expected = new ArrayList<>();
        Collections.addAll(expected, (double) 0, 1d, (double) 0);

        List<Double> actual = new ArrayList<>();
        actual.add(Calculator.getInstance().findRelationOfVolumesOfFigures(tetrahedron, new PlaneYZ(-4.5)));
        actual.add(Calculator.getInstance().findRelationOfVolumesOfFigures(tetrahedron, new PlaneYZ(tetrahedron.getCircle().getX())));
        actual.add(Calculator.getInstance().findRelationOfVolumesOfFigures(tetrahedron, new PlaneYZ(10.5)));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindRelationOfVolumesOfFiguresPlaneXY(){
        Tetrahedron tetrahedron = new Tetrahedron(new Point(1,1,2), new Point(1,1,1));

        double firstCase = 0;
        double secondCase = 1;
        double thirdCase = 0;

        double actualFirstCase = Calculator.getInstance().findRelationOfVolumesOfFigures(tetrahedron, new PlaneXY(-4.5));
        double actualSecondCase = Calculator.getInstance().findRelationOfVolumesOfFigures(tetrahedron, new PlaneXY(1.2062999999999773));
        double actualThirdCase = Calculator.getInstance().findRelationOfVolumesOfFigures(tetrahedron, new PlaneXY(10.5));

        Assert.assertEquals(actualFirstCase,firstCase, 0.001);
        Assert.assertEquals(actualSecondCase,secondCase, 0.001);
        Assert.assertEquals(actualThirdCase,thirdCase, 0.001);
    }


    @Test
    public void testIsBaseOnAxesValid() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(1,3,1), new Point(1,1,0));

        Assert.assertTrue(Calculator.getInstance().isBaseOnAxes(tetrahedron, Axes.XY));
    }

    @Test
    public void testIsBaseOnAxesNotValid() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(1,3,1), new Point(1,1,0));

        Assert.assertTrue(!Calculator.getInstance().isBaseOnAxes(tetrahedron, Axes.YZ));
    }
}