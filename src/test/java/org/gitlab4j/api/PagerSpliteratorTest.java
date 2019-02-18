package org.gitlab4j.api;

import java.util.Collections;
import java.util.stream.StreamSupport;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PagerSpliteratorTest {

	PagerSpliterator<Integer> pagerSpliterator;

	@Mock
	Pager<Integer> pager;

	@Before
	public void setUp() {
		pagerSpliterator = new PagerSpliterator<>(pager);
	}

	@Test
	public void shouldAcceptElementFromSource() {
		when(pager.hasNext()).thenReturn(true);
		when(pager.next()).thenReturn(Collections.singletonList(1));

		boolean success = pagerSpliterator.tryAdvance(System.out::println);

		assertTrue(success);
	}

	@Test
	public void shouldDoNotFailOnEmptySource() {
		boolean success = pagerSpliterator.tryAdvance(System.out::println);

		assertFalse(success);
	}

	@Test
	public void shouldThrowNullPointerExceptionWhenActionIsMissing() {
		try {
			pagerSpliterator.tryAdvance(null);
			Assert.fail("Missing NullPointerException");
		} catch (Throwable e) {
			assertEquals(NullPointerException.class, e.getClass());
		}
	}

	@Test
	public void shouldAllowToGatherDataPageByPage() {
		when(pager.hasNext()).thenReturn(true);
		when(pager.getTotalItems()).thenReturn(12);
		when(pager.next())
				.thenReturn(asList(1, 2, 3))
				.thenReturn(asList(4, 5, 6))
				.thenReturn(asList(7, 8, 9))
				.thenReturn(asList(0, 1, 2));

		Integer[] elements = StreamSupport.stream(new PagerSpliterator<Integer>(pager), false)
				.parallel() // should be ignored
				.skip(2) // should be ignored
				.limit(5)
				.toArray(Integer[]::new);

		assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, elements);
	}
}