package matchesinspace;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class homePane extends JFrame implements ActionListener{
	private optionPane options;
	private boardPane mainui;
	private JPanel mypanel;
	private JPanel mypanel2;
	private JButton mybutton1;
	private JButton mybutton2;
	private JButton mybutton3;
	private JLabel mylabel;
	
public homePane(){
	
	mypanel = new JPanel();
	
	setTitle("WHAT AN AWESOME GAME");
	setSize(400,300);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	setLayout(new GridLayout(3,1));
	
	mybutton1 = new JButton("New Game");
	mybutton2 = new JButton("Load Game");
	mybutton3 = new JButton("Settings");
	
	mybutton1.addActionListener(this);
	mybutton2.addActionListener(this);
	mybutton3.addActionListener(this);
	
	add(mybutton1);
	add(mybutton2);
	add(mybutton3);
	setVisible(true);
}

@Override
public void actionPerformed(ActionEvent event){
	if(event.getSource()== mybutton1)
	{
	mainui = new boardPane();
	setVisible(false);
	}
	if(event.getSource()== mybutton2)
	{
	mainui = new boardPane();
	setVisible(false);
	}
	if(event.getSource()== mybutton3)
	{
	options = new optionPane();
	
	setVisible(false);
	}	
}

}
