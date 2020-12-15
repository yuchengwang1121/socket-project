import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class login {
	static rules r;
	private static Frame frm1 = new Frame("login window");
	private static Label username = new Label("");
	private static Label password = new Label("");
	private static Label notice = new Label("", Label.CENTER);
	private static Label end = new Label("", Label.CENTER);
	private static TextField UN = new TextField();
	private static TextField PW = new TextField();
	private static Button Login = new Button("Login");
	private static Button Register = new Button("Register");
	private static Font font1 = new Font(Font.DIALOG, Font.BOLD, 15);
	private static Font font2 = new Font(Font.DIALOG_INPUT, Font.BOLD, 12);
	private static Font font3 = new Font(Font.DIALOG, Font.BOLD, 35);
	public static Point P;

	private static boolean loginsuccess = false;
	private static boolean registerfirst = false;
	private static boolean alreadyregister = false;

	public static BufferedWriter outputS;
	private static String servername = "127.0.0.1";
	private static int serverport = 4444;

	public static void main(String args[]) {

		new Thread(new Runnable() {// create thread to run client's socket
			@Override
			public void run() {

				SocketAddress serverSA = new InetSocketAddress(servername, serverport);

				try (Socket clientSocket = new Socket();) { // client's socket

					System.out.println("Connect to server" + servername + " : " + serverport);
					clientSocket.connect(serverSA, 3000); // connect the server in timeout 3000ms

					InetSocketAddress socketA = (InetSocketAddress) clientSocket.getLocalSocketAddress();
					String clientA = socketA.getAddress().getHostName(); // get local name
					int clientport = socketA.getPort(); // get client's port
					System.out.println("Client " + clientA + " : " + clientport);

					try {// create the input stream and output stream
						outputS = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
						BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

						char[] buf = new char[1024];
						int length = br.read(buf);
						String check = new String(buf, 0, length);
						while (length > 0) {//wait for server 
							if (check.equals("loginsuccess")) {//login success
								loginsuccess = true;
							} else if (check.equals("nothisaccount")) {//have register
								registerfirst = true;

							} else if (check.equals("alreadyregister")) {//have register
								alreadyregister = true;
							}else if (check.equals("lose")) {
								lose();
							}else if (check.equals("win")) {
								win();
							}else if(check.equals("samescore")){
								same();
							}
							
							length = br.read(buf);

							check = new String(buf, 0, length);
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					System.out.println("connect shutdown");
				}
			}
		}).start();

		int screen_width = 400;
		int screen_height = 400;

		frm1.setSize(screen_width, screen_height);
		frm1.setLayout(null); // cancel the setting of block
		frm1.setResizable(false); // make the window in certain size
		frm1.setLocationRelativeTo(null); // open at the middle of the computer
		frm1.setForeground(new Color(255, 165, 255));
		frm1.setBackground(new Color(255, 255, 200));

		username.setBounds(30, 70, 80, 30); // the location of user name
		username.setText("User ID ： ");
		username.setFont(font2);

		password.setBounds(30, 110, 80, 30); // the location of password
		password.setText("Password : ");
		password.setFont(font2);

		UN.setBounds(125, 70, 225, 30); // the location of input user name

		PW.setBounds(125, 110, 225, 30); // the location of input password
		PW.setEchoChar('*');

		Login.setBounds(250, 200, 70, 30); // the location of login
		Login.setFont(font1);
		Login.addActionListener(new ActLis());

		Register.setBounds(125, 200, 70, 30); // the location of login
		Register.setFont(font1);
		Register.addActionListener(new ActLis());

		notice.setBounds(125, 150, 200, 20); // the location of notice
		notice.setFont(font1);
		
		end.setBounds(50, 120, 300, 40);// the location of notice1
		end.setFont(font3);

		frm1.addWindowListener(new WindowAdapter() {
			public void// close
					windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frm1.add(username);
		frm1.add(password);
		frm1.add(UN);
		frm1.add(PW);
		frm1.add(Register);
		frm1.add(Login);
		frm1.setVisible(true);
	}

	public void createrule() {
		r = new rules();
	}
	public static void lose() {//if lose
		rules.frm.removeAll();
		rules.frm.repaint();

		end.setText("you lose QQ");
		
		rules.frm.add(end);
		rules.frm.revalidate();
	}
	public static void win() {//if win
		rules.frm.removeAll();
		rules.frm.repaint();

		end.setText("you win ~!!");
		
		rules.frm.add(end);
		rules.frm.revalidate();
	}
	public static void same() {//if same
		rules.frm.removeAll();
		rules.frm.repaint();

		end.setText("Tie");
		
		rules.frm.add(end);
		rules.frm.revalidate();
	}
	
	
	public static class ActLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Button btn = (Button) e.getSource();
			if (btn == Login) { // if press login
				String LUP = "L" + UN.getText() + " " + PW.getText();
				try {
					outputS.write(LUP);
					outputS.flush();
					Thread.sleep(100); // wait the server change boolean
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				if (registerfirst) { // if the account haven't been register
					notice.setText("Please register first.");
					notice.setFont(font2);
					frm1.add(notice);
					registerfirst = false;
				} else { // else if have register
					if (loginsuccess) { // if login success , go to game
						P = frm1.getLocation();
						frm1.setVisible(false);
						r = new rules();
						loginsuccess = false;
					} else { // type wrong user name or password
						notice.setText("Wrong password,type again.");
						notice.setFont(font2);
						frm1.add(notice);
					}
				}

			} else if (btn == Register) {

				String RUP = "R" + UN.getText() + " " + PW.getText();
				try {
					outputS.write(RUP);
					outputS.flush();
					Thread.sleep(100); // wait the server change boolean
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				if (alreadyregister) { // if have register
					notice.setText("You have already register.");
					notice.setFont(font2);
					frm1.add(notice);
					alreadyregister = false;
				} else {
					notice.setText("Register Success");
					frm1.add(notice);
				}
			}
		}

	}

}
