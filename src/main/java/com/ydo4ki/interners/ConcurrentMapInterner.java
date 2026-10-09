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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A version of {@link MapInterner} that uses a concurrent hash map.
 *
 * @see MapInterner
 * @author Sulphuris
 * @since 1.0.0 (22.10.2024 15:01)
 */
public class ConcurrentMapInterner<T> extends MapInterner<T> {
	/**
	 * Constructs a new interner using any given {@link ConcurrentMap} implementation.
	 * @param internMap {@link ConcurrentMap} used for interning
	 */
	protected ConcurrentMapInterner(ConcurrentMap<T, T> internMap) {
		super(internMap);
	}

	/**
	 * Constructs a new interner using {@link ConcurrentHashMap}.
	 */
	public ConcurrentMapInterner() {
		this(new ConcurrentHashMap<>());
	}

	/**
	 * Indicates whether this interner is safe to use in multithreaded environment.
	 * This particular interner implementation is always thread-safe, so this method always returns {@code true}.
	 *
	 * @return true
	 * @since 1.0.1
	 */
	@Override
	public final boolean isConcurrent() {
		return true;
	}
}