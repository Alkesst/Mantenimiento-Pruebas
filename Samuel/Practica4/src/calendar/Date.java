package calendar;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Date implements Comparable<Date> {
	private Day d;
	private Month m;
	private Year y;

	public Date(Month month, Day day, Year year) throws RuntimeException {
		d = day;
		m = month;
		y = year;
	}

	public Date(int pMonth, int pDay, int pYear) throws RuntimeException {
		y = new Year(pYear);
		m = new Month(pMonth, y);
		d = new Day(pDay, m);
	}

	public Date(String s) throws RuntimeException {
		int pMonth, pDay, pYear;
		try {
			Scanner scs = new Scanner(s);
			scs.useDelimiter("[/]+");
			pMonth = Integer.parseInt(scs.next());
			pDay = Integer.parseInt(scs.next());
			pYear = Integer.parseInt(scs.next());
			scs.close();
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Invalid date");
		}
		y = new Year(pYear);
		m = new Month(pMonth, y);
		d = new Day(pDay, m);
	}
	
	public Date parseDate(String s) throws RuntimeException {
		return new Date(s);
	}

	public void printDate() {
		System.out.println(m.getCurrentPos() + "/" + d.getCurrentPos() + "/" + y.getYear());
	}

	public String toString() {
		return m.getCurrentPos() + "/" + d.getCurrentPos() + "/" + y.getYear();
	}

	public void increment() {
		if (!d.increment()) {
			if (!m.increment()) {
				y.increment();
			}
			m.setMonth(1, y);
			d.setDay(1, m);
		}
	}

	public Year getYear() {
		return y;
	}

	public Month getMonth() {
		return m;
	}

	public Day getDay() {
		return d;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((m == null) ? 0 : m.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public int compareTo(Date o) {
		int res = y.compareTo(o.getYear());
		if (res < 0)
			return res;
		else {
			res = m.compareTo(o.getMonth());
			if (res < 0)
				return res;
			else
				return d.compareTo(o.getDay());
		}
	}
}
