package be.redlab.examples.databasetesting.common;

import java.io.Serializable;

/**
 * Based on Dick Wall's implementation:
 * http://www.developer.com/java/data/article.php/10932_3813031_1/Java-Needs-to-Get-a-Pair-and-a-Triple.htm
 *
 * @param <A>
 * @param <B>
 */
public final class Pair<A, B> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final A first;
	private final B second;

	private Pair(final A first, final B second) {
		this.first = first;
		this.second = second;
	}

	public static <A, B> Pair<A, B> of(final A first, final B second) {
		return new Pair<A, B>(first, second);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Pair<?, ?> other = (Pair<?, ?>) obj;
		if (this.first != other.first && (this.first == null || !this.first.equals(other.first))) {
			return false;
		}
		if (this.second != other.second && (this.second == null || !this.second.equals(other.second))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + (this.first != null ? this.first.hashCode() : 0);
		hash = 37 * hash + (this.second != null ? this.second.hashCode() : 0);
		return hash;
	}

	@Override
	public String toString() {
		return String.format("Pair[%s,%s]", first, second);
	}

  public A getFirst() {
    return first;
  }

  public B getSecond() {
    return second;
  }

}
