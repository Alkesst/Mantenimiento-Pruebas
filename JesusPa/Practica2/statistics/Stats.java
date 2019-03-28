/*
 * @author: Jesus Parejo Aliaga & Samuel Jurado Quintana
 */

package statistics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Stats {
	private static int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
	private int[] lst;
	private Map<Integer, Integer> frecs;

	public Stats(int[] lst) {
		if (lst == null || lst.length == 0)
			throw new IllegalArgumentException("Empty array");
		this.lst = lst;
		frecs = new HashMap<Integer, Integer>();
		for (int i = 0; i < lst.length; i++) {
			if (lst[i] < min) {
				min = lst[i];
			}
			if (lst[i] > max) {
				max = lst[i];
			}
			Integer n = frecs.get(lst[i]);
			if (n == null) {
				frecs.put(lst[i], 1);
			} else {
				frecs.put(lst[i], n + 1);
			}
		}
		Arrays.sort(lst);
	}

	public double media() {
		double sum = 0.0;
		for (int i = 0; i < lst.length; i++) {
			sum += lst[i];
		}
		return sum / lst.length;
	}

	public double mediana() {
		int middle = lst.length / 2;
		double median;
		if (lst.length % 2 == 0) {
			median = (lst[middle - 1] + lst[middle]) / 2.0;
		} else {
			median = lst[middle];
		}
		return median;
	}

	public double varianza() {
		double varsum = 0;
		for (int i = 0; i < lst.length; i++) {
			varsum += Math.pow(lst[i], 2);
		}
		return varsum / lst.length - Math.pow(media(), 2);
	}

	public int[] moda() {
		int mode_times = 0;
		boolean iguales = true;
		List<Integer> mode = new LinkedList<Integer>();
		for (Integer i : frecs.keySet()) {
			if (frecs.get(i) != mode_times) {
				iguales = false;
			}
			if (frecs.get(i) > mode_times) {
				mode.clear();
				mode_times = frecs.get(i);
			}
			if (frecs.get(i) >= mode_times) {
				mode.add(i);
			}
		}
		int[] result = new int[mode.size()];
		for (int i = 0; i < mode.size(); i++) {
			result[i] = mode.get(i);
		}
		return iguales ? null : result;
	}
}
