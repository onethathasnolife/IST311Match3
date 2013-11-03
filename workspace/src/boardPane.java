import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;


public class boardPane extends JFrame implements ActionListener {

private JPanel mainui;
private JButton testbutton;
private pausePane pause;

public boardPane(){
	
	setSize(1000,1000);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	
	testbutton = new JButton("PAUSE IT, BITCH");

	mainui = new JPanel();
	setLayout(new GridLayout(1,1));
	
	add(testbutton);
	
	testbutton.addActionListener(this);
	
	//setLayout(new BoxLayout(mainui, BoxLayout.PAGE_AXIS));
}

@Override
public void actionPerformed(ActionEvent event){
	if(event.getSource()== testbutton)
	{
	pause = new pausePane();
	setVisible(false);
	}		
	
}

}

