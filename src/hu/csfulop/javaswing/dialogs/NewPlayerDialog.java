package hu.csfulop.javaswing.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import hu.csfulop.javaswing.config.DataClass;

public class NewPlayerDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GridLayout gridLayout;
	private JTextField jtf;
	private NewGameDialog ngd;
	
	public NewPlayerDialog(NewGameDialog ngd) {
		this.ngd = ngd;
		this.setTitle(DataClass.newGameDialog);
		this.gridLayout = new GridLayout(2, 2);
		this.setLayout(this.gridLayout);
		initItems();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void initItems() {
		JLabel jl = new JLabel(DataClass.playerName);
		this.add(jl);
		this.jtf = new JTextField();
		this.add(this.jtf);
		JButton jb = new JButton(DataClass.okButton);
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
		} else if(e.getActionCommand().equals(DataClass.okButton)) {
			QueryClass.insert(this.jtf.getText());
			QueryClass.selectAll(this.ngd.getPlayerOne());
			QueryClass.selectAll(this.ngd.getPlayerTwo());
			this.setVisible(false);
		}
	}
	
}
