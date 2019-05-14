package calendar;

public class Day extends CalendarUnit {
	private Month m;

	public Day(int pDay, Month pMonth) {
		super(pDay);
		if (pMonth == null || pDay < 1 || pDay > pMonth.getMonthSize())
			throw new RuntimeException();
		m = pMonth;
	}

	public Month getMonth() {
		return m;
	}

	public void setDay(int pDay, Month pMonth) {
		setCurrentPos(pDay);
		m = pMonth;
	}

	@Override
	public boolean increment() {
		currentPos++;
		return (currentPos <= m.getMonthSize());
	}
}
