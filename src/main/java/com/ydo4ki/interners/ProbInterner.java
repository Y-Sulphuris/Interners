package com.ydo4ki.interners;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A probabilistic interner which delegates to the given parent interner with the given probability
 * (0.0 inclusive to 1.0 exclusive).
 *
 * @author Sulphuris
 * @since 22.10.2024 14:59
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
}
