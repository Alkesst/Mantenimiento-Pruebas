package calendar;

public abstract class CalendarUnit implements Comparable<CalendarUnit> {
	protected int currentPos;

	public CalendarUnit(int pCurrentPos) {
		currentPos = pCurrentPos;
	}

	public void setCurrentPos(int pCurrentPos) {
		currentPos = pCurrentPos;
	}

	public int getCurrentPos() {
		return currentPos;
	}

	protected abstract boolean increment();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPos;
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
		CalendarUnit other = (CalendarUnit) obj;
		if (currentPos != other.currentPos)
			return false;
		return true;
	}

	public int compareTo(CalendarUnit cu) {
		return new Integer(currentPos).compareTo(cu.currentPos);
	}
}
