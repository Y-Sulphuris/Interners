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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * A simple interner that stores its interned values in a map.
 *
 * @author Sulphuris
 * @since 1.0.0 (22.10.2024 14:46)
 */
public class MapInterner<T> implements Interner<T> {
	private final Map<T, T> intern_map;

	/**
	 * Constructs a new interner using any given {@link Map} implementation.
	 * @param internMap {@link Map} used for interning
	 */
	protected MapInterner(Map<T, T> internMap) {
		intern_map = internMap;
	}
	
	/**
	 * Constructs a new interner using {@link HashMap}.
	 */
	public MapInterner() {
		this(new HashMap<>());
	}
	
	/**
	 * Returns a canonical representation for the given object.
	 *
	 * @param value the value to be interned
	 * @return canonical value
	 */
	@Override
	public T intern(T value) {
		if (value == null)
			return null;

        return intern_map.computeIfAbsent(value, (k) -> k);
    }

	/**
	 * Indicates whether this interner is safe to use in multithreaded environment.
	 *
	 * @return true if the map used implements {@link ConcurrentMap}
	 * @since 1.0.1
	 */
	@Override
	public boolean isConcurrent() {
		return intern_map instanceof ConcurrentMap;
	}

	/**
	 * Clears the interner.
	 */
	public void clear() {
		intern_map.clear();
	}
}
