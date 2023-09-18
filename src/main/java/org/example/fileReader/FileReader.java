package org.example.fileReader;

import org.example.model.Product;
import org.example.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FileReader<T> {
    private Parser<T> parser;

    public FileReader(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readFromFile(String filePath, Map<String, Product> productMap) {
        List<T> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            // Possibly skip header, if needed
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                T obj = parser.parse(line, productMap);
                if (obj != null) {
                    results.add(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
