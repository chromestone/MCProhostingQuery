import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
public class Display {
	
	private JFrame frame;
	private JTextArea textArea;
	
	private final WebParser _parser;
	private final Display _display;
	
	public Display(WebParser parser) {
		frame = new JFrame("MCProhosting Query");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(790, 450);
		frame.setLocationRelativeTo(null);

		textArea = new JTextArea("Welcome to chromestone's MCProhosting Query", 50, 50);
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.add(scrollPane);

		JButton exitButton = new JButton("Exit");
		exitButton.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent event) {
				System.exit(0);
			}
		});
		panel.add(exitButton);

		_parser = parser;
		_display = this;
		JButton queryButton = new JButton("Pull Info");
		queryButton.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent event) {
				try {
				_display.append(_parser.parseMulticraft());
				}
				catch (Exception a) {
					_display.append("Error has occured please exit. Contact chromestone on the MCProhosting forums.\n" 
							+ a.getMessage());
				}
			}
		});
		panel.add(queryButton);

		frame.add(panel);
	}
	public void show() {
		frame.setVisible(true);
	}
	public void hide() {
		frame.setVisible(false);
	}
	public synchronized void append(String text) {
		if (!text.isEmpty()) {
			textArea.append("\n" + text);
		}
	}
	public void dispose() {
		frame.dispose();
	}
}
