import java.io.*;
import java.text.*;
import java.util.regex.*;
import java.util.*;
import java.net.*;
public class WebParser {
	
	private String _serverID;
	private String _playerNumber;
	
	public WebParser(String serverID, String playerNumber) {
		_serverID = serverID;
		_playerNumber = playerNumber;
	}

	public synchronized String parseMulticraft() throws Exception {
		String message = "";
		try {
			URL url = new URL("http://multicraft.mcprohosting.com/index.php?r=server/view&id=" + _serverID);
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
			Pattern pattern = Pattern.compile("<div id=\"statusdetail-ajax\">Online, " + ".+?" + "/" + _playerNumber +" players</div>");
			Matcher match = pattern.matcher(xmlString);
			if(match.find()) {
				String players = match.group();
				Pattern pattern2 = Pattern.compile(", " + ".+?" + "/");
				Matcher match2 = pattern2.matcher(players);
				if (match2.find()) {
					players = match2.group();
					players = players.substring(2, players.length()-1);
					if (Integer.parseInt(players) > 0) {
						java.awt.Toolkit.getDefaultToolkit().beep();
						DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
						Date date = new Date();
						message ="<" + dateFormat.format(date) + "> " + players + " Player(s) were online";
					}
				}
				else {
					message = "Did the website format change? or Did chromestone mess up?";
				}
			}
			else {
				message = "Did you enter invalid credentials? or Did the website format change?";
			}
			return message;
		}
		catch(Exception a) {
			throw a;
		}
	}

	public String[] parseVersion() throws Exception {
		try {
			URL url = new URL("http://chromestone.wix.com/mcpqueryversion?_escaped_fragment_=");
			URLConnection con = url.openConnection();
			InputStream is =con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			StringBuilder xmlBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				xmlBuilder.append(line);
			}
			String xmlString = xmlBuilder.toString();
			Pattern pattern = Pattern.compile("<p class=\"font_8\">" + ".+?" + "</p>");
			Matcher match = pattern.matcher(xmlString);
			String[] webPkg = new String[3];
			if(match.find()) {
				String info = match.group();
				info = info.substring(18,info.length()-4);
				webPkg = info.split("¬†");
			}
			else {
				webPkg[0] = "";
				webPkg[1] = "";
			}
			return webPkg;
		}
		catch (Exception a) {
			throw a;
		}
	}
}

