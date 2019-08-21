package com.epam.task1_6.scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    private static final String TETRAHEDRON_INPUT_DELIMITER = " ";
    private static final String DOUBLE_INPUT_REGEX = "[+-]?((\\d+\\.?\\d*)|(\\.\\d+))";
    private static final Logger LOGGER = LogManager.getLogger(FileReader.class);

    public List<String> read(String path) throws IOException {
        LOGGER.entry();

        if(!isFileExists(path)){
            LOGGER.fatal("File was not found!");
            throw new FileNotFoundException("File was not found!");
        }

        Path pathFile = Paths.get(path);

        Stream<String> lineStream =
                Files.lines(pathFile)
                        .filter(FileReader::isTetrahedron);

        List<String> result = lineStream.collect(Collectors.toList());
        return LOGGER.exit(result);
    }

    private static boolean isTetrahedron(String line) {
        if (line.equals("")) {
            return false;
        }
        String[] array = line.split(TETRAHEDRON_INPUT_DELIMITER);
        if(array.length != 6){
            return false;
        }
        for (String s : array) {
            if (!s.matches(DOUBLE_INPUT_REGEX)) {
                return false;
            }
        }
        return true;
    }

    private boolean isFileExists(String path){
        LOGGER.entry();

        File f = new File(path);
        boolean result = f.exists()&&!f.isDirectory();
        return LOGGER.exit(result);
    }
}
