package org.gitlab4j.api;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PagerSpliteratorTest {

	PagerSpliterator<Integer> pagerSpliterator;

	@Mock
	Pager<Integer> pager;

	@BeforeEach
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
	public void shouldReturnFalseIfNextPagerItemMissing() {
		when(pager.hasNext()).thenReturn(true);
		when(pager.next()).thenReturn(Collections.emptyList());

		boolean success = pagerSpliterator.tryAdvance(System.out::println);

		assertFalse(success);
	}

	@Test
	public void shouldThrowNullPointerExceptionWhenActionIsMissing() {
		try {
			pagerSpliterator.tryAdvance(null);
			fail("Missing NullPointerException");
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
