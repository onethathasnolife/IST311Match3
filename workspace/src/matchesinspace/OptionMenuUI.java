package matchesinspace;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class OptionMenuUI extends JFrame implements ActionListener {

	private MainMenuUI mainui;
	private JButton sound,grahpics,misc,main;
	
	public OptionMenuUI(){
		
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
		mainui = new MainMenuUI();
		setVisible(false);
		}
		if(event.getSource()==sound ||event.getSource()==misc||event.getSource()==grahpics){
			JOptionPane.showMessageDialog(mainui,"Your Clicking of the Button was noticed, this notice has been dully noted and recorded for furture procescution");
		}
	}

}
