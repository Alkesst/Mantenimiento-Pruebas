package calendar;

public class Month extends CalendarUnit {
	private Year y;
	private int[] sizeIndex = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public Month(int pCur, Year pYear) {
		super(pCur);
		if (pYear == null || 1 > pCur || pCur > 12)
			throw new RuntimeException();
		y = pYear;
	}

	public int getMonthSize() {
		if (currentPos == 2 && y.isLeap())
			return 29;
		else
			return sizeIndex[currentPos - 1];
	}

	public Year getYear() {
		return y;
	}

	@Override
	public boolean increment() {
		currentPos++;
		return (currentPos <= 12);
	}

	public void setMonth(int pcur, Year pYear) {
		setCurrentPos(pcur);
		y = pYear;
	}
}
