package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.JsonExample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileParser {

    public String readFileContentAsString(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("ERROR: No file %s found", fileName);
        }
    }

    public JsonExample readFileContentAsJsonPOJO(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(fileName), JsonExample.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonExample();
        }
    }

    public InputStream readFileContentAsInputStream(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return InputStream.nullInputStream();
        }
    }
}
