package com.ydo4ki.interners;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple interner that stores its interned values in a map.
 *
 * @author Sulphuris
 * @since 22.10.2024 14:46
 */
public class MapInterner<T> implements Interner<T> {
	private final Map<T, T> intern_map;
	
	protected MapInterner(Map<T, T> internMap) {
		intern_map = internMap;
	}
	
	/**
	 * Constructs a new interner.
	 */
	public MapInterner() {
		this(new HashMap<>());
	}
	
	/**
	 * Clears the interner.
	 */
	public void clear() {
		intern_map.clear();
	}
	
	/**
	 * Returns a canonical representation for the given object.
	 *
	 * @param value the value to be interned
	 * @return canonical value
	 */
	@Override
	public T intern(T value) {
		T ob = intern_map.computeIfAbsent(value, (k) -> k);
		if (ob != null) return ob;
		return value;
	}
}
