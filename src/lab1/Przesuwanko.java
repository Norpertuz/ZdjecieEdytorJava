package lab1;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Przesuwanko extends JPanel {
	public JTextField textfield;
	public JButton ok;
	public JLabel avalue;
	public int a,b;
	public JTextField textfield2;

	public Przesuwanko() {
		setLayout(null);
		avalue = new JLabel("Przesuniecie X:");
		avalue.setBounds(10, 14, 118, 14);
		add(avalue);
		textfield = new JTextField();
		textfield.setBounds(141, 11, 168, 20);
		add(textfield);
		textfield.setColumns(10);
		ok = new JButton("Accept");
		ok.setBounds(86, 73, 162, 35);
		add(ok);	
		
		textfield2 = new JTextField();
		textfield2.setBounds(141, 42, 168, 20);
		add(textfield2);
		textfield2.setColumns(10);
		
		JLabel bvalue = new JLabel("Przesuniecie Y:");
		bvalue.setBounds(10, 45, 118, 14);
		add(bvalue);
		
	}
}
