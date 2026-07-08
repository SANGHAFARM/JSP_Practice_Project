package round25.out;

import java.io.IOException;
import java.io.Reader;

public class DataOut {
	private Reader in;

	public Reader getIn() {
		return in;
	}

	public void setIn(Reader in) {
		this.in = in;
		viewData();
	}

	public void viewData() {
		try {
			while (true) {
				int x = in.read();
				if (x == -1)
					break;
				System.out.print((char) x);
			}
		} catch (IOException ie) {
		} finally {
			try {
				in.close();
			} catch (IOException ie) {
			}
		}
	}
}
