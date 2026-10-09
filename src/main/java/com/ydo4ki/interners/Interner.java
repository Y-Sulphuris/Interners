/*
 * MIT License
 *
 * Copyright (c) 2024-2026 Sulphuris
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ydo4ki.interners;

/**
 * This interface represents an interner.
 *
 * @param <T> the type of value to be interned (not necessarily implements {@link Internable}). 
 * @see String#intern()
 * @author Sulphuris
 * @since 1.0.0
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
     * Indicates whether this interner is safe to use in multithreaded environment.
     *
     * @return true if this interner thread-safe, false otherwise or if unknown
     * @since 1.0.1
     */
    default boolean isConcurrent() {
        return false;
    }
	
    /**
     * An interner implementation using the built-in String interning mechanism.
     */
	Interner<String> vmStringInterner = new Interner<String>() {
        @Override
        public String intern(String s) {
            if (s == null)
                return null;

            return s.intern();
        }

        @Override
        public boolean isConcurrent() {
            return true;
        }
    };
}