package org.gitlab4j.api;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class PagerSpliterator<T> implements Spliterator<T> {

	private Pager<T> pager;

	private Iterator<T> elements;

	PagerSpliterator(Pager<T> pager) {
		this.pager = pager;
		if (pager.hasNext()) {
			elements = this.pager.next().iterator();
		}
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		if (elements.hasNext()) {
			action.accept(elements.next());
			return true;
		} else if (pager.hasNext()) {
			elements = pager.next().iterator();
			action.accept(elements.next());
			return true;
		}
		return false;
	}

	@Override
	public Spliterator<T> trySplit() {
		return null;
	}

	@Override
	public long estimateSize() {
		return pager.getTotalItems();
	}

	@Override
	public int characteristics() {
		return 0;
	}
}
