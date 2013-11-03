import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class pausePane extends JFrame implements ActionListener{
	
	private JButton unpause;
	private JButton save;
	private boardPane resume;

	public pausePane(){
		setTitle("WHAT AN AWESOME GAME");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		setLayout(new GridLayout(2,1));
		
		unpause = new JButton("UNPAUSE ME, BITCH");
		save = new JButton("Save Da Game");
		
		add(unpause);
		add(save);
		
		unpause.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event){	
		if(event.getSource()== unpause)
		{
		resume = new boardPane();
		setVisible(false);
		}		
	}

}
