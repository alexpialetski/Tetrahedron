package com.epam.task1_6.scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReaderTest {

    @Test
    public void testRead() throws IOException {
        FileReader fileReader = new FileReader();

        List<String> expected = new ArrayList<>();
        Collections.addAll(expected, "1.0 1.0 1.0 1.0 1.0 2.0",
                "2.0 2.0 2.0 2.0 2.0 5.0");

        List<String> actual = fileReader.read(".\\src\\main\\resources\\coordinates.txt");

        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = FileNotFoundException.class)
    public void nullSuppliedRead() throws IOException {
        FileReader fileReader = new FileReader();
        fileReader.read(".\\src\\main\\resources\\cooordinates.txt");
    }
}