/**
 *
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 *
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {

		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}


	/**
	 * Sorts a list using a Comparator object.
	 *		- Least significant digit first
	 						Use k-indexed counting on characters, moving right to left
	 * 		- Source: http://algs4.cs.princeton.edu/51radix/LSD.java.html
	 * @param a the array to be sorted
	 * @param w the number of characters per string
	 * @return
	 */
	public void LSDradixSort(String[] a, int w) {
		int n = a.length;
		int R = 256;   // extend ASCII alphabet size
		String[] aux = new String[n];

		for (int d = w-1; d >= 0; d--) {
				// sort by key-indexed counting on dth character

				// compute frequency counts
				int[] count = new int[R+1];
				for (int i = 0; i < n; i++)
						count[a[i].charAt(d) + 1]++;

				// compute cumulates
				for (int r = 0; r < R; r++)
						count[r+1] += count[r];

				// move data
				for (int i = 0; i < n; i++)
						aux[count[a[i].charAt(d)]++] = a[i];

				// copy back
				for (int i = 0; i < n; i++)
						a[i] = aux[i];

	  }
	}


	/**
	 * Sorts a list using a Comparator object.
	 *
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 *
	 * Returns a list that might be new.
	 *
	 * @param list
	 * @param comparator
	 * @return
	 */

	 public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
		 int size = list.size();
		 if(size < 2) { return list; }

		 int splitPoint = size/2;
		 List<T> leftList = mergeSort(new LinkedList<>(list.subList(0, splitPoint)), comparator);
		 List<T> rightList = mergeSort(new LinkedList<>(list.subList(splitPoint, size)), comparator);

		 return merge(leftList, rightList, comparator);
	}

	private List<T> merge(List<T> leftList, List<T> rightList, Comparator<T> comparator) {
		List<T> sortedList = new LinkedList<T>();
		int size = leftList.size() + rightList.size();

		for (int i = 0; i < size; i++) {

			while(leftList.size() > 0 && rightList.size() > 0) {
				int comp = comparator.compare(leftList.get(0), rightList.get(0));
				if(comp < 0 || comp == 0) { sortedList.add(leftList.remove(0)); }
				else { sortedList.add(rightList.remove(0)); }
			}

			if(leftList.size() == 0 && rightList.size() == 0) { break; }
			else if(leftList.size() == 0) { sortedList.add(rightList.remove(0)); }
			else if(rightList.size() == 0) { sortedList.add(leftList.remove(0)); }

		}
		return sortedList;
	}


	/*public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // Old iterative version

				if(list.size() < 2) { return list; }

				int splitPoint = list.size()/2;
				List<T> leftList = new LinkedList<>();
				List<T> rightList = new LinkedList<>();

				for(int i = 0; i < list.size(); i++) {
					if(i < splitPoint) {
						leftList.add(list.get(i));
					} else if(i >= splitPoint) {
						rightList.add(list.get(i));
					}
				}

				insertionSort(leftList, comparator);
				insertionSort(rightList, comparator);

				int k = 0;
				List<T> sortedList = new LinkedList<>(leftList);
				for(int j = sortedList.size(); j < list.size(); j++) {
					sortedList.add(j, rightList.get(k));
					k++;
				}

				insertionSort(sortedList, comparator);
        return sortedList;
	}*/




	/**
	 * Sorts a list using a Comparator object.
	 *
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> heap = new PriorityQueue(list.size(), comparator);
				for(T elem: list) {
					heap.offer(elem);
				}

				list.clear();
				while(heap.size() > 0) {
					list.add(heap.poll());
				}
	}


	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 *
	 * @param k
	 * @param list
	 * @param comparator
	 * @return
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
			PriorityQueue<T> heap = new PriorityQueue(list.size(), comparator);
			for(T elem: list) {
				if(heap.size() < k) {
					heap.offer(elem);
				}

				//if heap is at capacity, check if next element in list is larger than heap element
				//if so, remove the smallest element from heap and add the list element
				else {
					int comp = comparator.compare(elem, heap.peek());
					if(comp > 0) {
						heap.poll();
						heap.offer(elem);
					}
				}

			}

			list.clear();
			while(heap.size() > 0) {
				list.add(heap.poll());
			}

        return list;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));

		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};

		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
