package matchesinspace;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
/**
 * 
 * Contains all information related to the main menu UI
 *
 */
public class MainMenuUI extends JFrame implements ActionListener{
	private OptionMenuUI options;
	private BoardUI mainui;
	private JPanel mypanel,mypanel2;
	private JButton mybutton1,mybutton2,mybutton3;
	private JLabel mylabel;
/**
 * Constructor, sets up main information of the UI
 */
public MainMenuUI(){
	
	mypanel = new JPanel();
	
	setTitle("MATCHES..........IN SPACE!!!!");
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
/**
 * Handles information to button presses on this UI
 * @param event set of event that can have an action
 */
public void actionPerformed(ActionEvent event){
	if(event.getSource()== mybutton1)
	{
	mainui = new BoardUI();
	setVisible(false);
	}
	if(event.getSource()== mybutton2)
	{
	mainui = new BoardUI();
	setVisible(false);
	}
	if(event.getSource()== mybutton3)
	{
	options = new OptionMenuUI();
	
	setVisible(false);
	}	
}

}
