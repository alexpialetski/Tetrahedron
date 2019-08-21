package com.epam.task1_6.planes;

import com.epam.task1_6.entity.Point;
import com.epam.task1_6.entity.Tetrahedron;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaneXYTest {

    @Test
    public void testGetVolumeOfPartOfRegularTetrahedron() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(2,1.5073, 2.633), new Point(2, 1.5073, 1));

        double expected = 0.1177597125;

        double actual = new PlaneXY(1.8165).getVolumeOfPart(tetrahedron);

        Assert.assertEquals(actual, expected, 0.0001);
    }

    @Test
    public void testGetVolumeOfPartOfUpsideDownTetrahedron() {
        Tetrahedron upsideDownTetrahedron = new Tetrahedron(new Point(2,1.5073, -0.633), new Point(2, 1.5073, 1));

        double expected = 0.1177597125;

        double actual = new PlaneXY(0.1835).getVolumeOfPart(upsideDownTetrahedron);

        Assert.assertEquals(actual, expected, 0.0001);
    }

    @Test
    public void testIsTouchingTetrahedron1() {
        Tetrahedron tetrahedron = new Tetrahedron(new Point(2,1, 4), new Point(2, 1, 2));
        Tetrahedron upsideDownTetrahedron = new Tetrahedron(new Point(2,1, 0), new Point(2, 1, -4));

        List<Boolean> expected = new ArrayList<>();
        Collections.addAll(expected, false, true, false, false, true, false);

        List<Boolean> actual = new ArrayList<>();
        actual.add(new PlaneXY(1.1)
                .isTouchingTetrahedron(tetrahedron));
        actual.add(new PlaneXY(3)
                .isTouchingTetrahedron(tetrahedron));
        actual.add(new PlaneXY(5)
                .isTouchingTetrahedron(tetrahedron));
        actual.add(new PlaneXY(1.1)
                .isTouchingTetrahedron(upsideDownTetrahedron));
        actual.add(new PlaneXY(-3)
                .isTouchingTetrahedron(upsideDownTetrahedron));
        actual.add(new PlaneXY(-5)
                .isTouchingTetrahedron(upsideDownTetrahedron));

        Assert.assertEquals(actual,expected);
    }
}