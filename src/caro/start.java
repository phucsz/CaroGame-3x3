package caro;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class start extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[][] btn;
	private JButton btnNewGame;
	private int chk = 0;
	private int[][] a;
	public start() {
		setSize(500,500);
		setTitle("Caro");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 3));	
		btn = new JButton[3][3];
		add(p);
		a = new int[3][3];
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				btn[i][j] = new JButton();
				p.add(btn[i][j]);
				int row = i;
				int col = j;
				btn[i][j].addActionListener(e-> {
					Check(btn[row][col],row,col);
				});
			}
		}
		JPanel pButton = new JPanel();
		p.add(pButton);
		pButton.add(btnNewGame = new JButton("New Game"));
		btnNewGame.addActionListener(e -> ResetGame());
	}
	
	public static void main(String[] args) {
		new start().setVisible(true);
	}

	public void Check(JButton btn, int row, int col) {
		if (btn.getText() != "") return;
		if (chk != -1) {
			btn.setText("O");
			btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
			chk = -1;
			btn.setForeground(Color.RED);
			if (CheckWin(row,col,chk)) {
				JOptionPane.showMessageDialog(this,"O WIN");
				return;
			}
		}
		else {
			btn.setText("X");
			btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
			chk = 1;
			btn.setForeground(Color.black);
			if (CheckWin(row,col,chk)) {
				JOptionPane.showMessageDialog(this,"X WIN");
				return;
			}
		}
		if (CheckDraw()) {
			JOptionPane.showMessageDialog(this,"DRAW");
			return;
		}
	}
	public boolean CheckWin(int row,int col, int chk) {
		a[row][col] = chk;
		if (a[row][0] == chk && a[row][1] == chk && a[row][2] == chk) return true;
		if (a[0][col] == chk && a[1][col] == chk && a[2][col] == chk) return true;
		if (col == row) {
			if (a[0][0] == chk && a[1][1] == chk && a[2][2] == chk) return true;
		}
		if (row + col == 2) {
			if (a[0][2] == chk && a[1][1] == chk && a[2][0] == chk) return true;
		}
		return false;
	}
	public boolean CheckDraw() {
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (a[i][j]== 0) return false;
			}
		}
		return true;
	}
	public void ResetGame() {
		chk = 0;
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				a[i][j] = 0;
				btn[i][j].setText("");
			}
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		}
}
