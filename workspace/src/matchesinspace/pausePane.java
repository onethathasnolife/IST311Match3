package matchesinspace;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class pausePane extends JFrame implements ActionListener {
	private JPanel mainui;
	private homePane homeui;
	private JPanel mypanel;
	private JButton mybutton1,mybutton2;
	
	public pausePane(JPanel game){
		mainui = game;
		mypanel = new JPanel();
		setTitle("MATCHES..........IN SPACE!!!!");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new GridLayout(2,1));
		
		mybutton1 = new JButton("Unpause Game");
		mybutton2 = new JButton("Return to main menu");
		
		mybutton1.addActionListener(this);
		mybutton2.addActionListener(this);
		
		add(mybutton1);
		add(mybutton2);
		
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == mybutton1){
			setVisible(false);
			mainui.setVisible(true);
		}
		if(e.getSource() == mybutton2){
			setVisible(false);
			mainui.setVisible(false);
			homeui = new homePane();
		}
	}
}