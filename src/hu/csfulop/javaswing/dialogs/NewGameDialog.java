package hu.csfulop.javaswing.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import hu.csfulop.javaswing.MainWindow;
import hu.csfulop.javaswing.config.DataClass;

public class NewGameDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GridLayout gridLayout;
	private JComboBox<String> playerOne, playerTwo;
	private MainWindow mw;
	
	public NewGameDialog(MainWindow mw) {
		this.mw = mw;
		this.setTitle(DataClass.newGameDialog);
		this.gridLayout = new GridLayout(4, 2);
		this.setLayout(this.gridLayout);
		initItems();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void initItems() {
		JLabel jl = new JLabel(DataClass.playerOne);
		this.add(jl);
		playerOne = new JComboBox<String>();
		QueryClass.selectAll(playerOne);
		this.add(playerOne);
		jl = new JLabel(DataClass.playerTwo);
		this.add(jl);
		playerTwo = new JComboBox<String>();
		QueryClass.selectAll(playerTwo);
		this.add(playerTwo);
		jl = new JLabel(DataClass.newPlayer);
		this.add(jl);
		JButton jb = new JButton(DataClass.newPlayerButton);
		this.add(jb);
		jb.addActionListener(this);
		jb = new JButton(DataClass.okButton);
		this.add(jb);
		jb.addActionListener(this);
		jb = new JButton(DataClass.cancelButton);
		this.add(jb);
		jb.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(DataClass.cancelButton)) {
			this.setVisible(false);
		} else if (e.getActionCommand().equals(DataClass.newPlayerButton)) {
			new NewPlayerDialog(this);
		} else if(e.getActionCommand().equals(DataClass.okButton)) {
			this.setVisible(false);
			this.mw.changeTableVisibility();
			QueryClass.insertNewMatch();
			new NewThrowDialog(this.mw);
		}
	}

	public JComboBox<String> getPlayerOne() {
		return playerOne;
	}

	public JComboBox<String> getPlayerTwo() {
		return playerTwo;
	}
	
	
	
}
