package org.example.parser;
import org.example.model.Product;

import java.util.Map;

public class ProductParser implements Parser<Product> {
    @Override
    public Product parse(String line, Map<String, Product> productMap) {
        String[] parts = line.split(",");
        return new Product(parts[0].trim(), Double.parseDouble(parts[1].trim()));
    }
}
