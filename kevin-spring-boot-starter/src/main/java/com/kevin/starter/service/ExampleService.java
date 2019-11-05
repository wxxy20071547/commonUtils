package com.kevin.starter.service;

/**
 * ExampleService
 *
 * @author kevin
 * @version 1.0
 */
public class ExampleService {

    private String prefix;
    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }
    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
