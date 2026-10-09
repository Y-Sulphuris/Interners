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

import java.util.concurrent.ThreadLocalRandom;

/**
 * A probabilistic interner which delegates to the given parent interner with the given probability
 * (0.0 inclusive to 1.0 exclusive).
 *
 * @author Sulphuris
 * @since 1.0.0 (22.10.2024 14:59)
 */
public class ProbInterner<T> implements Interner<T> {
	private final Interner<T> parent;
	private final int prob;
	
	/**
	 * Constructs a new probabilistic interner.
	 *
	 * @param parent the parent interner which will be delegated to with the given probability
	 * @param chance the probability the given parent interner will be delegated to (0.0 inclusive to 1.0 exclusive)
	 */
	public ProbInterner(Interner<T> parent, float chance) {
		this.parent = parent;
		this.prob = (int) (Integer.MIN_VALUE + chance * (1L << 32));
	}

	/**
	 * Returns deduplicated representation for the given object (interns it with {@link ProbInterner#prob} chance)
	 *
	 * @param value the value to be interned
	 * @return canonical value
	 */
	@Override
	public T intern(T value) {
		return ThreadLocalRandom.current().nextInt() > prob ? value : parent.intern(value);
	}

	/**
	 * Indicates whether this interner is safe to use in multithreaded environment.
	 *
	 * @return true if the parent interner is concurrent, false otherwise
	 * @since 1.0.1
	 */
	@Override
	public boolean isConcurrent() {
		return parent.isConcurrent();
	}
}
