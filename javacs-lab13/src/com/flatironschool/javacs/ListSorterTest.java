/**
 *
 */
package com.flatironschool.javacs;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author downey
 *
 */
public class ListSorterTest {

	private ListSorter<Integer> sorter;
	private Comparator<Integer> comparator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};

		sorter = new ListSorter<Integer>();
	}

	@Test
	public void testLSDRadixSort() {
		String[] arr = new String [] {"aab", "aaa", "lop", "bbf", "bbd", "abc"};
		ListSorter<String> stringSorter = new ListSorter<>();
		stringSorter.LSDradixSort(arr, 3);
		assertThat(arr.length, is(6));
		assertThat(arr[0], is("aaa"));
		assertThat(arr[1], is("aab"));
		assertThat(arr[2], is("abc"));
		assertThat(arr[3], is("bbd"));
		assertThat(arr[4], is("bbf"));
		assertThat(arr[5], is("lop"));
	}

	/**
	 * Test method for {@link com.flatironschool.javacs.ListSorter#insertionSort(java.util.List, java.util.Comparator)}.
	 */
	@Test
	public void testInsertionSort() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.insertionSort(list, comparator);
		isSorted(list);
	}

	/**
	 * @param list
	 *
	 */
	private void isSorted(List<Integer> list) {
		assertThat(list.size(), is(5));
		assertThat(list.get(0), is(1));
		assertThat(list.get(1), is(2));
		assertThat(list.get(2), is(3));
		assertThat(list.get(3), is(4));
		assertThat(list.get(4), is(5));
	}

	/**
	 * Test method for {@link com.flatironschool.javacs.ListSorter#mergeSortInPlace(java.util.List, java.util.Comparator)}.
	 */
	@Test
	public void testMergeSortInPlace() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		isSorted(list);
	}

	/**
	 * Test method for {@link com.flatironschool.javacs.ListSorter#mergeSort(java.util.List, java.util.Comparator)}.
	 */
	@Test
	public void testMergeSort() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		List<Integer> sorted = sorter.mergeSort(list, comparator);
		isSorted(sorted);

		//Base case test
		List<Integer> secondList = new ArrayList<>(Arrays.asList(6));
		List<Integer> secondSorted = sorter.mergeSort(secondList, comparator);
		assertThat(secondSorted.get(0), is(6));
	}

	/**
	 * Test method for {@link com.flatironschool.javacs.ListSorter#heapSort(java.util.List, java.util.Comparator)}.
	 */
	@Test
	public void testHeapSort() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		isSorted(list);
	}

	/**
	 * Test method for {@link com.flatironschool.javacs.ListSorter#topK(int, java.util.List, java.util.Comparator)}.
	 */
	@Test
	public void testTopK() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));

		List<Integer> res = sorter.topK(4, list, comparator);
		assertThat(res.size(), is(4));
		assertThat(res.get(0), is(5));
		assertThat(res.get(1), is(6));
		assertThat(res.get(2), is(7));
		assertThat(res.get(3), is(8));
	}
}
