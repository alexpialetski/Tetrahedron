package com.epam.task1_6.action;

import com.epam.task1_6.entity.Point;
import com.epam.task1_6.entity.Tetrahedron;
import com.epam.task1_6.scanner.FileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TetrahedronFactoryTest {

    @Test
    public void testCreateTetrahedron() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> points = fileReader.read(".\\src\\main\\resources\\coordinates.txt");

        List<Tetrahedron> expected = new ArrayList<>();
        expected.add(new Tetrahedron(new Point(1.0 ,1.0, 1.0), new Point(1.0, 1.0, 2.0)));
        expected.add(new Tetrahedron(new Point(2.0, 2.0, 2.0), new Point(2.0, 2.0, 5.0)));

        List<Tetrahedron> actual = TetrahedronFactory.getInstance()
                .createTetrahedrons(points);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetListOfPoints() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> points = fileReader.read(".\\src\\main\\resources\\coordinates.txt");

        List<Point> expected = new ArrayList<>();
        expected.add(new Point(1.0 ,1.0, 1.0));
        expected.add(new Point(1.0, 1.0, 2.0));
        expected.add(new Point(2.0, 2.0, 2.0));
        expected.add(new Point(2.0, 2.0, 5.0));

        List<Point> actual = TetrahedronFactory.getInstance()
                .getListOfPoints(points);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetListOfPointsErrors() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> points = fileReader.read(".\\src\\main\\resources\\coordinates.txt");

        List<Point> expected = new ArrayList<>();
        expected.add(new Point(1.0 ,1.0, 1.0));
        expected.add(new Point(1.0, 1.0, 2.0));
        expected.add(new Point(2.0, 2.0, 2.0));
        expected.add(new Point(2.0, 2.0, 5.0));

        List<Point> actual = TetrahedronFactory.getInstance()
                .getListOfPoints(points);

        Assert.assertEquals(expected, actual);
    }
}