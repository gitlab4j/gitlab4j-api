package org.gitlab4j.api;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PagerSpliteratorTest {

	PagerSpliterator<Integer> pagerSpliterator;

	@Mock
	Pager pager;

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
}