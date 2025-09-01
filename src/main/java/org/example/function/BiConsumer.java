package org.example.function;


/**
 * Represents an operation that accepts two input arguments and returns no result
 *
 * @see java.util.function.Consumer
 * @see java.util.function.BiFunction
 */
@FunctionalInterface
@Deprecated
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
