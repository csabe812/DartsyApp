package hu.csfulop.javaswing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import hu.csfulop.javaswing.config.*;
import hu.csfulop.javaswing.dialogs.NewGameDialog;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param title the title of the frame
	 * This method creates the frame and the menu
	 */
	public MainWindow(String title) {
		super(title);
		this.setSize(800, 600);
		initMenuBar();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * This method creates the whole menu (including the menubar - menu - menu item)
	 */
	public void initMenuBar() {
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu(DataClass.fileMenu);
		for(int i = 0; i < DataClass.fileMenuItem.length; i++) {
			JMenuItem jmi = new JMenuItem(DataClass.fileMenuItem[i]);
			jmi.addActionListener(this);
			jm.add(jmi);
		}
		jmb.add(jm);
		setJMenuBar(jmb);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().toUpperCase().equals("NEW")) {
			new NewGameDialog();
		} else if(e.getActionCommand().toUpperCase().equals("EXIT")) {
			System.exit(0);
		}
	}
	
}
