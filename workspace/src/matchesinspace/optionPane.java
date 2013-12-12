package matchesinspace;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class optionPane extends JFrame implements ActionListener {

	private homePane mainui;
	private JButton sound;
	private JButton grahpics;
	private JButton misc;
	private JButton main;
	
	public optionPane(){
		
		setTitle("Matches................IN SPACE!!!!");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new GridLayout(4,1));
		
		sound = new JButton("Sound");
		grahpics = new JButton("Grahpics");//NICE JOB STEVE
		misc = new JButton("Misc");
		main = new JButton("Back To Main Menu");
		
		sound.addActionListener(this);
		grahpics.addActionListener(this);
		misc.addActionListener(this);
		main.addActionListener(this);
		
		add(sound);
		add(grahpics);
		add(misc);
		add(main);
		
		setVisible(true);	
	}
	
	@Override
	public void actionPerformed(ActionEvent event){
		if(event.getSource()== main)
		{
		mainui = new homePane();
		setVisible(false);
		}	
	}

}
