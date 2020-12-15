import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class rules {
//	private static login login=new login();
	public static Frame frm = new Frame("Guessing number");
	private static Label howtoplay = new Label("0", Label.CENTER);
	private static Label guessnum = new Label("0");
	private static Label score = new Label("0");
	private static Label notice = new Label("");
	private static Label end = new Label("",Label.CENTER);
	private static Panel pal = new Panel(new GridLayout(3, 3));// 3*3blocks
	private static Panel pal1 = new Panel(new GridLayout(1, 1));// "0" blocks
	private static Panel pal2 = new Panel(new GridLayout(1, 1));// Enter blocks
	private static Button digits[] = new Button[10];
	private static Button enter;
	private static Button clean;
	private static Font font1 = new Font(Font.DIALOG, Font.BOLD, 25);
	private static Font font2 = new Font(Font.DIALOG, Font.BOLD, 15);
	private static Point P = new Point(login.P);

	public rules() {
//public static void main(String args[]){

		int screen_width = 400;
		int screen_height = 400;
		int Playerscore = 10;

		frm.setSize(screen_width, screen_height);
		frm.setLayout(null);// cancel the setting of block
		frm.setResizable(false);// make the window in certain size
		frm.setLocation(P);
		frm.setBackground(new Color(255, 255, 200));

		howtoplay.setBounds(40, 40, 325, 30);// the location of text
		howtoplay.setText("Please enter the number between 1 to 100");
		howtoplay.setFont(font2);
		howtoplay.setBackground(new Color(255, 230, 170));

		score.setBounds(300, 100, 100, 30);// the location of text
		score.setText("Score : " + Playerscore);
		score.setFont(font2);

		guessnum.setBounds(180, 80, 100, 20);// the location of num
		guessnum.setFont(font1);

		notice.setBounds(170, 120, 100, 20);// the location of notice
		notice.setFont(font1);

		end.setBounds(50, 120, 300, 20);// the location of notice
		end.setFont(font2);

		pal.setBounds(50, 150, 200, 200);// the location of button 1-9
		pal1.setBounds(250, 150, 100, 67);// the location of button 1-9
		pal2.setBounds(250, 217, 100, 133);// the location of button enter
		pal.setFont(font1);
		pal1.setFont(font1);
		pal2.setFont(font2);

		digits[0] = new Button(Integer.toString(0));// add the bottom 0
		pal1.add(digits[0]);
		digits[0].addActionListener(new ActLis());
		for (int i = 9; i >= 1; i--) {// add the button from 1 to 9
			digits[i] = new Button(Integer.toString(i));
			pal.add(digits[i]);
			digits[i].addActionListener(new ActLis());
		}

		enter = new Button("Enter");
		pal2.add(enter);
		enter.addActionListener(new ActLis());

		clean = new Button("Clean");
		pal2.add(clean);
		clean.addActionListener(new ActLis());

		frm.addWindowListener(new WindowAdapter() {
			public void// close
					windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frm.add(howtoplay);
		frm.add(guessnum);
		frm.add(notice);
		frm.add(score);
		frm.add(pal);
		frm.add(pal1);
		frm.add(pal2);
		frm.setVisible(true);
	}

	public static class ActLis implements ActionListener {
		int Playerscore = 10;
		long result;
		int number = new Random().nextInt(100) + 1;

		public void actionPerformed(ActionEvent e) throws NumberFormatException {

			Button btn = (Button) e.getSource();
			try { // the number from 1-9
				for (int i = 0; i <= 9; i++) {
					if (btn == digits[i]) {
						output_digit(digits[i]);
						break;
					}
				}
				if (btn == enter) { // when press the enter
					result = Long.parseLong(guessnum.getText());
					check(result);
					guessnum.setText("");
				} else if (btn == clean) {
					guessnum.setText("");
				}

			} catch (NumberFormatException ne) { // the exception
			}

		}

		private void output_digit(Button btn) {
			guessnum.setText(Long.toString(Long.parseLong(guessnum.getText() + btn.getLabel())));
		}

		private void check(long x) {

			if (Playerscore == 0) {
				frm.removeAll();
				frm.repaint();
				
				end.setText("you have no score to use, please wait.");

				frm.add(end);
				frm.revalidate();
			} else if (x != number) { // if not equal
				notice.setText(x < number ? "Higher..." : "Lower...");
				Playerscore = Playerscore - 1;
				score.setText("Score : " + Playerscore);

			} else { // if correct store the score and wait //if same
				frm.removeAll();
				frm.repaint();

				end.setText("Please wait another player for seconds");
				try {
					String s = Integer.toString(Playerscore);
					login.outputS.write(s);
					login.outputS.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frm.add(end);
				frm.revalidate();
			}
		}
	}

}
