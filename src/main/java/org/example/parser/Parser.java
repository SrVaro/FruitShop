package org.example.parser;

import org.example.model.Product;

import java.util.Map;

public interface Parser<T> {
    T parse(String line,  Map<String, Product> productMap);
}