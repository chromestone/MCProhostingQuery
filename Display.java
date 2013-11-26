import javax.swing.*;
import javax.swing.event.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class Display {
	
	private JFrame frame;
	private JPanel buttonPanel;
	
	private final JTextArea textArea;
	private final WebParser _parser;
	private final Display _display;
	
	public Display(WebParser parser) {
		frame = new JFrame("MCProhosting Query 1.3.6");
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);

		textArea = new JTextArea("Welcome to chromestone's MCProhosting Query", 25, 50);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.add(scrollPane);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.setOpaque(true);
		
		_parser = parser;
		_display = this;
		createPullInfoButton();
		
		createClearButton();
		
		createExitButton();
		
		frame.add(buttonPanel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	private void createExitButton() {
		JButton exitButton = new JButton("Exit");
		exitButton.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent event) {
				System.exit(0);
			}
		});
		buttonPanel.add(exitButton);
	}
	
	private void createPullInfoButton() {
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
		buttonPanel.add(queryButton);
	}
	
	private void createClearButton() {
		JButton clearButton = new JButton("Clear");
		clearButton.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent event) {
				textArea.setText("Welcome to chromestone's MCProhosting Query");
			}
		});
		buttonPanel.add(clearButton);
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
