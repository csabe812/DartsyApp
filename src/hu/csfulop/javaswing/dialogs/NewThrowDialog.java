package hu.csfulop.javaswing.dialogs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import hu.csfulop.javaswing.MainWindow;
import hu.csfulop.javaswing.config.DataClass;

public class NewThrowDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GridLayout gridLayout;
	private JTextField jtf;
	private MainWindow mw;
	
	public NewThrowDialog(MainWindow mw) {
		this.mw = mw;
		this.setTitle(DataClass.newGameDialog);
		this.gridLayout = new GridLayout(5, 2);
		this.setLayout(this.gridLayout);
		initItems();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void initItems() {
		JLabel jl = new JLabel(DataClass.followingUserTurn);
		this.add(jl);
		jl = new JLabel(DataClass.followingUserTurn);
		this.add(jl);
		jl = new JLabel(DataClass.scoresLeft);
		this.add(jl);
		jl = new JLabel(DataClass.scoresLeft);
		this.add(jl);
		jl = new JLabel(DataClass.enterScore);
		this.add(jl);
		this.jtf = new JTextField();
		this.add(this.jtf);
		jl = new JLabel(DataClass.modifyPreviousScore);
		this.add(jl);
		JButton jb = new JButton(DataClass.modify);
		this.add(jb);
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
		} else if(e.getActionCommand().equals(DataClass.okButton)) {
			int thrownValue = Integer.parseInt(this.jtf.getText());
			if(thrownValue < 0 || thrownValue > 180) {
				JFrame jf = new JFrame();
				JOptionPane.showMessageDialog(jf, DataClass.valueError, DataClass.valueErrorTitle, JOptionPane.WARNING_MESSAGE);
			} else {
				QueryClass.insertNewThrow(Integer.parseInt(this.jtf.getText()));
				QueryClass.selectThrows(this.mw);
			}
		}
	}
	
}
