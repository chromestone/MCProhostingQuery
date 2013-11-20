public class MCPQuery {

	private StartForm form;
	private Triplet triplet;
	private Display display;

	private MulticraftParser parser;

	public MCPQuery() {
		form = new StartForm();
		triplet = form.getInfo();
		parser = new MulticraftParser((String)triplet.getTwo(), (String)triplet.getThree());
		display = new Display(parser);
	}
	private void run() {
		try {
			String tripletOne;
			if (!(tripletOne = (String)triplet.getOne() ).equals("")) {
				display.show();
				int sleepTime = Integer.parseInt(tripletOne.substring(0,2));
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

	public static void main(String args[]) {
		MCPQuery query = new MCPQuery();
		query.run();
	}
}
