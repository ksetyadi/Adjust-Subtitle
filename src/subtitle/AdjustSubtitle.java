package subtitle;

import java.util.Scanner;

public class AdjustSubtitle {
	public static final int MODE_ADD = 0;
	public static final int MODE_SUB = 1;

	private static int CHANGED = 0;
	private static int mode = -1;

	public static void main(String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("add")) {
				mode = MODE_ADD;
			} else if (args[0].equalsIgnoreCase("sub")) {
				mode = MODE_SUB;
			} else {
				System.out.println("Parameter '" + args[0] + "' not recognized.");
				mode = -1;
				return;
			}
		} else {
			System.out.println("Need parameters.");
			return;
		}

		try {
			CHANGED = Integer.valueOf(args[1]);
		} catch (NumberFormatException e) {
			System.out.println("'" + args[1] + "' is not a number.");
			return;
		}

		Scanner s = new Scanner(System.in);

		String line = null;

		while (s.hasNextLine()) {
			line = s.nextLine();

			if (line.indexOf("-->") != -1) {

				String[] lines = line.split("-->");

				for (int i = 0; i < lines.length; i++) {
					if (i == 1)
						System.out.print(" --> ");
					
					TimeTrack timeTrack = new TimeTrack(lines[i], mode, CHANGED);

					if (i == 0) {
						System.out.print(timeTrack.getFinalResult());
					} else {
						System.out.println(timeTrack.getFinalResult());
					}
				}
			} else {
				// write everything else
				System.out.println(line);
			}
		}
	}
}
