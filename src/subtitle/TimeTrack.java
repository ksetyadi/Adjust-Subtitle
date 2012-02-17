package subtitle;

public class TimeTrack {

	private int hours;
	private int minutes;
	private int seconds;
	private int milliseconds;

	private int mode;
	private int modification;

	public TimeTrack(String timeTrackStr, int mode, int modification) {
		this.mode = mode;
		this.modification = modification;

		String[] timeArr = timeTrackStr.split(":");
		this.hours = Integer.valueOf(timeArr[0].trim());
		this.minutes = Integer.valueOf(timeArr[1]);
		this.seconds = Integer.valueOf(timeArr[2].substring(0,
				timeArr[2].indexOf(",")));
		this.milliseconds = Integer.valueOf(timeArr[2].substring(
				timeArr[2].indexOf(",") + 1, timeArr[2].length()).trim());
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getModification() {
		return modification;
	}

	public void setModification(int modification) {
		this.modification = modification;
	}
	
	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public String getFinalResult() {
		int newSeconds = seconds;
		int newMinutes = minutes;
		int newHours = hours;

		newSeconds = (seconds + modification) % 60;

		if (mode == AdjustSubtitle.MODE_ADD && newSeconds < seconds) {
			newMinutes = ++minutes % 60;

			if (newMinutes < minutes) {
				// you probably don't need the (modulo 24) part because no movie
				// last for a day. You might want to add the modulo part someday,
				// if such any movie exists.
				newHours = ++hours % 24;
			}
		}

		if (mode == AdjustSubtitle.MODE_SUB && newSeconds > seconds) {
			newMinutes = --minutes % 60;

			if (newMinutes > minutes) {
				// you probably don't need the (modulo 24) part because no movie
				// last for a day. You might want to add the modulo part someday,
				// if such any movie exists.
				newHours = --hours % 24;
			}
		}

		StringBuffer resultBuf = new StringBuffer();
		resultBuf.append(newHours < 10 ? "0" : "").append(newHours).append(":")
				.append(newMinutes < 10 ? "0" : "").append(newMinutes).append(":")
				.append(newSeconds < 10 ? "0" : "").append(newSeconds).append(",")
				.append(milliseconds);

		return resultBuf.toString();
	}

}
