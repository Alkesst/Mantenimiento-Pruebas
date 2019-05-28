package calendar;

public class Year extends CalendarUnit {

	public Year(int pYear) {
		super(pYear);
	}

	@Override
	public boolean increment() {
		currentPos++;
		return true;
	}

	public int getYear() {
		return currentPos;
	}

	public boolean isLeap() {
		return (currentPos % 4 == 0 && !(currentPos % 400 == 0) || (currentPos % 400 == 0));
	}
}
