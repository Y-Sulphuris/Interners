package com.ydo4ki.interners;

import java.util.concurrent.ConcurrentHashMap;

/**
 * A version of {@link MapInterner} that uses a concurrent hash map.
 *
 * @see MapInterner
 * @author Sulphuris
 * @since 22.10.2024 15:01
 */
public class ConcurrentMapInterner<T> extends MapInterner<T> {
	public ConcurrentMapInterner() {
		super(new ConcurrentHashMap<>());
	}
}
