public class MCPQuery {

	private StartForm form;
	private Triplet triplet;
	private Display display;
	private WebParser parser;

	private final String version = "1.2.3";

	public MCPQuery() {
		form = new StartForm();
		triplet = form.getInfo();
		display = new Display(parser);
		parser = new WebParser((String)triplet.getTwo(), (String)triplet.getThree());
	}
	private void run() {
		try {
			String tripletOne;
			if (!(tripletOne = (String)triplet.getOne() ).equals("")) {
				int sleepTime = Integer.parseInt(tripletOne.substring(0,2));
				display.show();
				getVersion();
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

	private void getVersion() throws Exception{
		try {
			String[] webPkg = parser.parseVersion();
			if (!webPkg[0].equals("")) {
				display.append("Version " + webPkg[0]);
				if (webPkg[0].equals(version)) {
					System.out.println("New version available at " + webPkg[1]);
				}
			}
			else {
				display.append("Unable to get version number.");
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
