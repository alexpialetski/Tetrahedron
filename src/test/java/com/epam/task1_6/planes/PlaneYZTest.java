package com.epam.task1_6.planes;

import com.epam.task1_6.action.Calculator;
import com.epam.task1_6.entity.Point;
import com.epam.task1_6.entity.Tetrahedron;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaneYZTest {

    @Test
    public void testIsTouchingTetrahedron() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(2,1, 4), new Point(2, 1, 2));

        List<Boolean> expected = new ArrayList<>();
        Collections.addAll(expected, false, true, false);

        List<Boolean> actual = new ArrayList<>();
        actual.add(new PlaneYZ(-4)
                .isTouchingTetrahedron(tetrahedron));
        actual.add(new PlaneYZ(1)
                .isTouchingTetrahedron(tetrahedron));
        actual.add(new PlaneYZ(5)
                .isTouchingTetrahedron(tetrahedron));

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testGetVolumeOfPart() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(2,1, 4), new Point(2, 1, 2));

        double expected = Calculator.getInstance()
                .findVolumeOfTetrahedron(tetrahedron)/2;

        double actual = new PlaneYZ(tetrahedron.getCircle().getX())
                .getVolumeOfPart(tetrahedron);

        Assert.assertEquals(actual,expected,0.0001);
    }
}