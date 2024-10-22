package com.ydo4ki.interners;


/**
 * Provides utility methods for implementing the {@link Internable}
 * interface.
 *
 * @author Sulphuris
 * @see Internable
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
		if (interner == null) return (T) this;
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

