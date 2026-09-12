package caro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class start extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[][] btn;
	private JButton btnNewGame,btnResetPoint,btnExitGame;
	private int chk = 0;
	private int Opts,Xpts;
	private int[][] a;
	private JLabel lblXpts = new JLabel("X points: " + Xpts);
	private JLabel lblOpts = new JLabel("O points: " + Opts);
	
	public start() {
		setSize(500,600);
		setTitle("Caro Game 3x3");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
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
		
		//Panel chứa nút và thống kê
		JPanel pBoard = new JPanel();
		pBoard.setLayout(new BoxLayout(pBoard, BoxLayout.X_AXIS));
		add(pBoard,BorderLayout.SOUTH);
		
		//Các button chức năng
		JPanel pButton = new JPanel();
		pButton.setLayout(new FlowLayout());
		pButton.add(btnNewGame = new JButton("New Game"));
		btnNewGame.addActionListener(e -> ResetGame());
		pButton.add(btnResetPoint = new JButton("Reset Point"));
		btnResetPoint.addActionListener(e -> ResetPoint());
		pButton.add(btnExitGame = new JButton("Exit"));
		btnExitGame.addActionListener(e -> { System.exit(0);});
		
		//Thống kê ingame
		JPanel pStats = new JPanel();
		pStats.setLayout(new BoxLayout(pStats, BoxLayout.Y_AXIS));
		
		lblXpts.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblOpts.setAlignmentX(Component.CENTER_ALIGNMENT);
		pStats.add(Box.createVerticalGlue());
		pStats.add(lblXpts);
		pStats.add(Box.createVerticalStrut(10)); 
		pStats.add(lblOpts);
		pStats.add(Box.createVerticalStrut(50)); 
		lblOpts.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		lblXpts.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		
		pBoard.add(pButton);
		pBoard.add(pStats);
		
		
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
				ResetGame();
				UpdatePoints(lblOpts,'O');
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
				ResetGame();
				UpdatePoints(lblXpts,'X');
				return;
			}
		}
		if (CheckDraw()) {
			JOptionPane.showMessageDialog(this,"DRAW");
			ResetGame();
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
	public void UpdatePoints(JLabel pts,char check) {
		int plusPoint;
		if (check == 'X') {
			Xpts++;
			plusPoint = Xpts;
		}
		else {
			Opts++;
			plusPoint = Opts;
		}
		pts.setText(check+ " points: " + (plusPoint));
	}
	public void ResetPoint() {
		Xpts = 0;
		Opts = 0;
		lblXpts.setText("X points: " + Xpts);
		lblOpts.setText("O points: " + Opts);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		}
}
