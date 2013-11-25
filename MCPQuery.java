public class MCPQuery {

	private StartForm form;
	private Triplet triplet;
	private WebParser parser;
	private Display display;

	private final String version = "1.3.5";

	public MCPQuery() {
		form = new StartForm();
		triplet = form.getInfo();
		parser = new WebParser((String)triplet.getTwo(), (String)triplet.getThree());
		display = new Display(parser);
	}
	private void run() {
		try {
			String tripletOne;
			if (!(tripletOne = (String)triplet.getOne() ).equals("")) {
				int sleepTime = Integer.parseInt(tripletOne.substring(0,2));
				display.show();
				displayVersion();
				while (true) {
					display.append(parser.parseMulticraft());
					Thread.sleep(sleepTime * 60 * 1000);
				}
			}
		}
		catch(Exception a) {
			display.append("Error has occured please exit. Contact chromestone on the MCProhosting forums.\n" 
					+ a.getMessage());
		}
	}

	private void displayVersion() throws Exception{
		try {
			display.append("Version " + version);
			String[] webPkg = parser.parseVersion();
			if (!webPkg[0].equals("")) {
				if (!webPkg[0].equals(version)) {
					display.append("Version " + webPkg[0] + " available at\n" + webPkg[1]);
				}
			}
		}
		catch (Exception a) {
			throw a;
		}
	}

	public static void main(String args[]) {
		MCPQuery query = new MCPQuery();
		query.run();
	}
}
