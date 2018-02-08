package io.github.biezhi.java8.growing.jdk8;

import java.util.function.Supplier;

public interface DefaulableFactory {
    // Interfaces now allow static methods
    static Integer create(Supplier<Integer> supplier) {
        return supplier.get();
    }
}