/*
 * @author: Jesus Parejo Aliaga & Samuel Jurado Quintana
 */

package statistics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatsNumber {
	private double[] lst;
	private Map<Double, Integer> frecs;

	public StatsNumber(Number[] lst) {
		if (lst == null || lst.length == 0)
			throw new IllegalArgumentException("Empty array");
		this.lst = new double[lst.length];
		for (int i = 0; i < lst.length; i++) {
			if (lst[i] == null)
				throw new IllegalArgumentException("Null element in array");
			this.lst[i] = lst[i].doubleValue();
		}
		Arrays.sort(this.lst);
		frecs = new HashMap<Double, Integer>();
		for (int i = 0; i < lst.length; i++) {
			Integer n = frecs.get(this.lst[i]);
			if (n == null) {
				frecs.put(this.lst[i], 1);
			} else {
				frecs.put(this.lst[i], n + 1);
			}
		}
	}

	public Number media() {
		double sum = 0.0;
		for (int i = 0; i < lst.length; i++) {
			sum += lst[i];
		}
		return sum / lst.length;
	}

	public Number mediana() {
		int middle = lst.length / 2;
		double median;
		if (lst.length % 2 == 0) {
			median = (lst[middle - 1] + lst[middle]) / 2;
		} else {
			median = lst[middle];
		}
		return median;
	}

	public Number varianza() {
		double varsum = 0;
		for (int i = 0; i < lst.length; i++) {
			varsum += Math.pow(lst[i], 2);
		}
		return varsum / lst.length - Math.pow(media().doubleValue(), 2.0);
	}

	public Number[] moda() {
		int mode_times = 0;
		boolean iguales = true;
		List<Number> mode = new LinkedList<Number>();
		for (Number i : frecs.keySet()) {
			if (!mode.isEmpty() && frecs.get(i) != mode_times) {
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
		Number[] result = new Number[mode.size()];
		for (int i = 0; i < mode.size(); i++) {
			result[i] = mode.get(i);
		}
		return iguales ? null : result;
	}
}