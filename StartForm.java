import java.awt.*;
import javax.swing.*;

public class StartForm {
	
	public Triplet getInfo() {
		String[] items = new String[51];
		for (int minutes = 10; minutes < 61; minutes++) {
			items[minutes-10] = Integer.toString(minutes) + " minutes";
		}
		JComboBox combo = new JComboBox(items);
		JTextField field1 = new JTextField("0");
		JTextField field2 = new JTextField("0");
		JTextField field3 = new JTextField("0");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(combo);
		panel.add(new JLabel("Server ID(view&id=******):"));
		panel.add(field1);
		panel.add(new JLabel("Max Number of Players:"));
		panel.add(field2);
		panel.add(new JLabel("Multicraft Root Link(E.g. https://multicraft.mcprohosting.com/):"));
		panel.add(field2);
		int result = JOptionPane.showConfirmDialog(null, panel, "Test",
												   JOptionPane.OK_CANCEL_OPTION,
												   JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			Triplet triplet = new Triplet(items[combo.getSelectedIndex()], field1.getText(), field2.getText(), field3.getText());
			return triplet;
		}
		else {
			Triplet triplet = new Triplet("", "", "", "");
			return triplet;
		}
	}
}
