package hu.csfulop.javaswing;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import hu.csfulop.javaswing.config.*;
import hu.csfulop.javaswing.dialogs.NewGameDialog;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable jt;

	/**
	 * 
	 * @param title the title of the frame
	 * This method creates the frame and the menu
	 */
	public MainWindow(String title) {
		super(title);
		Image icon = Toolkit.getDefaultToolkit().getImage(DataClass.frameIconPath);  
		this.setIconImage(icon);  
		this.setSize(800, 600);
		initMenuBar();
		initTable();
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

	public void initTable() {
		String data[][] = { { "101", "Amit", "670000" }, { "102", "Jai", "780000" }, { "101", "Sachin", "700000" } };
		String column[] = DataClass.tableColumnName;
		this.jt = new JTable(data, column);
//		jt.setBounds(30, 40, 200, 300);
		JScrollPane sp = new JScrollPane(this.jt);
		this.add(sp);
		this.jt.setVisible(false);
	}
	
	public void changeTableVisibility() {
		this.jt.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(DataClass.fileMenuItem[0])) {
			new NewGameDialog(this);
		} else if(e.getActionCommand().equals(DataClass.fileMenuItem[1])) {
			System.exit(0);
		}
	}
	
}
