package com.ydo4ki.interners;

/**
 * This interface represents an interner.
 *
 * @param <T> the type of value to be interned
 * @see String#intern()
 */
@FunctionalInterface
public interface Interner<T> {
    /**
     * Returns a canonical representation for the given object.
     *
     * @param value the value to be interned
     * @return canonical value
     */
    T intern(T value);
	
    /**
     * An interner implementation using the built-in String interning mechanism.
     */
	Interner<String> vmStringInterner = String::intern;
}