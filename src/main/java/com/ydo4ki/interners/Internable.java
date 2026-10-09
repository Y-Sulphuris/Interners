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
 * Provides utility methods for implementing the {@link Internable}
 * interface.
 *
 * @see Internable
 * @author Sulphuris
 * @since 1.0.0
 */
public interface Internable<T extends Internable<T>> {

	/**
	 * Returns an interner for the given object. If the returned interner is not
	 * null, the object will be interned when the {@link #intern()} method is
	 * called.
	 *
	 * @return An interner for the given object.
	 */
	default Interner<T> interner() {
		return null;
	}

	/**
	 * Interns the given object by using the interner returned by
	 * {@link #interner()}. If the returned interner is null, the object is
	 * returned as is.
	 *
	 * @return The interned object.
	 */
	@SuppressWarnings("unchecked")
	default T intern() {
		Interner<T> interner = this.interner();
		if (interner == null)
			return (T) this;

		return interner.intern((T) this);
	}

	/**
	 * Interns the given object by using the interner returned by
	 * {@link #interner()}. If the returned interner is null, the object is
	 * returned as is.
	 *
	 * @param x the object to be interned
	 * @return The interned object.
	 */
	static <X extends Internable<X>> X intern(X x) {
		if (x == null) return null;
		return x.intern();
	}
}

