package runner;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import utilities.Constants;
import utilities.XmlUtils;

public class MainUiRunner {
	static JCheckBox check;
	static JFrame frame;
	static JButton button;
	static JTextArea uid1;
	static JTextArea uid2;
	static JPanel panel;
	static List<JCheckBox> checkboxes;
	static List<JCheckBox> selectedCheckboxes;
	static List<String> selectedTestCases;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createUI();

	}

	private static void createUI() {
		// TODO Auto-generated method stub
		
		frame = new JFrame("JioChat test");
		frame.setIconImage(new ImageIcon("./res/icon.png").getImage());
		// frame.setIconImage(icon);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel uidpanel = new JPanel(new GridLayout(2, 2));
		Border gridboorder = BorderFactory.createLineBorder(Color.BLACK);
		// uidpanel.setBorder(gridboorder);
		JLabel uid1label = new JLabel("Test Device ID");
		// uid1label.setBorder(gridboorder);
		uid1 = new JTextArea("");
		uid1.setBorder(gridboorder);
		JLabel uid2label = new JLabel("Verification Device ID");
		// uid2label.setBorder(gridboorder);
		uid2 = new JTextArea("");
		uid2.setBorder(gridboorder);
		uidpanel.add(uid1label);
		uidpanel.add(uid1);

		uidpanel.add(uid2label);
		uidpanel.add(uid2);
		panel = new JPanel(new GridLayout(0, 1));
		Border border = BorderFactory.createTitledBorder("Test cases");
		panel.setBorder(border);
		// JCheckBox check = new JCheckBox("Chat test");
		check = new JCheckBox("Chat test");
		JCheckBox check1 = new JCheckBox("Explore Test cases");
		JCheckBox check2 = new JCheckBox("public channels Test cases");
		JCheckBox check3 = new JCheckBox("Contacts Test cases");
		JCheckBox check4 = new JCheckBox("Settings Test cases");
		JCheckBox check5 = new JCheckBox("Chat session Test cases");
		JCheckBox check6 = new JCheckBox("Group chat Test cases");
		JCheckBox check7 = new JCheckBox("Voice call Test cases");
		JCheckBox check8 = new JCheckBox("Video call Test cases");
		JCheckBox check9 = new JCheckBox("Sticker Test cases");

		CheckboxGroup group1 = new CheckboxGroup();

		panel.add(check);
		panel.add(check1);
		panel.add(check2);
		panel.add(check3);
		panel.add(check4);
		panel.add(check5);
		panel.add(check6);
		panel.add(check7);
		panel.add(check8);
		panel.add(check9);

		button = new JButton("Run");
		button.setActionCommand("1");
		// button.addActionListener(this);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Button Clicked");
				if (uid1.getText() != null)
					System.out.println(uid1.getText().trim());
				Constants.UDID1=uid1.getText().trim();
				if (uid2.getText() != null)
					System.out.println(uid2.getText().trim());
				Constants.UDID2=uid2.getText().trim();

				checkboxes = new ArrayList<JCheckBox>();
				for (Component comp : panel.getComponents()) {
					if (comp instanceof JCheckBox)
						checkboxes.add((JCheckBox) comp);
				}
				selectedTestCases = new ArrayList<String>();
				for (JCheckBox cmp : checkboxes) {
					if (cmp.isSelected()) {
						selectedTestCases.add(cmp.getText());
					}

				}
				XmlUtils.generateXmlSuites(selectedTestCases);
			}

		});
		Container contentPane = frame.getContentPane();
		contentPane.add(uidpanel, BorderLayout.NORTH);
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(button, BorderLayout.SOUTH);
		frame.setSize(400, 600);
		frame.setVisible(true);
	}

}
