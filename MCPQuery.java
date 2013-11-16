import java.util.*;
import java.util.regex.*;
import java.net.*;
import java.io.*;
import java.text.*;

public class MCPQuery {
	
	private void run() {
		StartForm form = new StartForm();
		Quad quad = form.getInfo();
		Display display = new Display();
		try {
			String tOne;
			if (!(tOne = (String)quad.getOne() ).equals("")) {
				display.show();
				tOne = tOne.substring(0,2);
			while (true) {
				URL url = new URL((String)quad.getFour() + "index.php?r=server/view&id=" + (String)triplet.getTwo());
				URLConnection con = url.openConnection();
				String redirect = con.getHeaderField("Location");
				if (redirect != null){
					con = new URL(redirect).openConnection();
				}
				InputStream is =con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line = null;
				StringBuilder xmlBuilder = new StringBuilder();
				while ((line = br.readLine()) != null) {
					xmlBuilder.append(line);
				}
				br.close();
				String xmlString = xmlBuilder.toString();
				Pattern pattern = Pattern.compile("<div id=\"statusdetail-ajax\">Online, " + ".?" + "/" + (String)quad.getThree() +" players</div>");
				Matcher match = pattern.matcher(xmlString);
				if(match.find()) {
					String players = match.group();
					Pattern pattern2 = Pattern.compile(" " + ".?" + "/");
					Matcher match2 = pattern2.matcher(players);
					if (match2.find()) {
						players = match2.group();
						players = players.substring(1, players.length()-1);
						if (Integer.parseInt(players) > 0) {
							java.awt.Toolkit.getDefaultToolkit().beep();
							DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
							Date date = new Date();
							display.append("<" + dateFormat.format(date) + "> " + players + " Player(s) were online");
						}
					}
					else {
						display.append("Error has occured please exit.\nDid the website format change? or Did chromestone mess up? Did you remember the trailing slash at the end of the multicraft root link?");
						break;
					}
				}
				else {
					display.append("Error has occured please exit.\nDid you enter invalid credentials? or Did the website format change?");
					break;
				}
				Thread.sleep(Integer.parseInt(tOne) * 60 * 1000);
			}
			}
		}
		catch(Exception a) {
			display.append("Error has occured please exit. Contact chromestone on the MCProhosting forums.");
		}
	}
	
   public static void main(String args[]) {
	   MCPQuery query = new MCPQuery();
	   query.run();
   }
}
