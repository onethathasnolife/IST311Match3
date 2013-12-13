package matchesinspace;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;


public class GameUI extends JFrame implements ActionListener,KeyListener {

private JPanel mainui;
private JButton testbutton;
private PauseMenuUI pause;

public BoardUI(){
	
	
	setSize(1000,1000);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	
	mainui = new JPanel();
	setLayout(new GridLayout(1,1));
	
	JOptionPane.showMessageDialog(mainui,"To Pause Press P");
	addKeyListener(this);
	
	//setLayout(new BoxLayout(mainui, BoxLayout.PAGE_AXIS));
}

@Override
public void actionPerformed(ActionEvent event){
		
	
}

@Override
public void keyPressed(KeyEvent e) {
	if(e.getKeyChar()=='p'){
		pause = new PauseMenuUI(mainui);
	}	
}

@Override
public void keyReleased(KeyEvent e) {
		
	
}

@Override
public void keyTyped(KeyEvent e) {
	
	
}

}

