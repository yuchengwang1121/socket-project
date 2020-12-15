import java.net.*; 
import java.io.*; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class server { 
public static Map<String, String> map = new HashMap<>();
public static int counter = 0;
public static int front;
public static int back;
public static List<Socket> sockets = new Vector<>();

@SuppressWarnings("resource")
public static void main(String[] args) throws IOException { 
     
    boolean listening = true; 
    String servername = "127.0.0.1";
    int serverport = 4444;
    String pathname = "C:\\Users\\wangyucheng\\Desktop\\Account.txt";
    
    InetSocketAddress serverSA = new InetSocketAddress(servername, serverport);
    String local = serverSA.getAddress().getHostAddress();
    
    //create a BUFreader to see whether the account is in the file
    BufferedReader readaccount = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)));
    char[] database = new char[1024];
    int lengthofdata = readaccount.read(database);			//read the account into batabase
    while(lengthofdata > 0) {								//have read things
    	String[] cutted = new String(database,0,lengthofdata).split(" |\r\n");//split while there are " " or next line
    	for(int i=0; i<cutted.length; i+=2) {				//map the user name and password
    		String s = cutted[i];
    		char newcut[]=new char[cutted[i].length()];
    		s.getChars(1,cutted[i].length(),newcut,0);		//remove the "R" to map
    		String S = new String(newcut, 0, newcut.length);
    		cutted[i] = S;									//give it back to cutted
    		map.put(cutted[i], cutted[i+1]);
    	}
    	break;
    }
    
    try(ServerSocket serverSocket = new ServerSocket();) { 	//if can't connect to 4444 then throw IOExeception
    	System.out.print("Bind server socket to : " + local + ":" + serverport);
        serverSocket.bind(serverSA);
        System.out.print("\nBind success\n");
     
        while (listening) {
        	Socket socket = new Socket();
        	socket = serverSocket.accept();
        	synchronized (sockets){
                sockets.add(socket);
            }
        	Thread task = new Thread(new clientHandleTask(socket));
        	task.start();
        }
    } catch (IOException e) {								//when there are error in I/O
     e.printStackTrace();; 									//print error message
     System.exit(-1); 										//close the progress in not normal mode
    } finally {
    	System.out.print("shut down");
    }
    
} 

public static class clientHandleTask implements Runnable{
	
	public Socket socket = null;
	private static BufferedWriter out = null;
    private static BufferedReader reader = null;
	
	public clientHandleTask(Socket socket) throws IOException {
		this.socket = socket;
		out = new BufferedWriter 
				(new OutputStreamWriter
				(socket.getOutputStream())); 				//create new print writer from current Output stream of socket
	    reader = new BufferedReader
	    		(new InputStreamReader
	    		(socket.getInputStream()));					// read from socket
	}

	@Override
	public void run() {
		try { 
		    
		    char[] buf = new char[1024];
		    int length = reader.read(buf);
		    while (length > 0) {							//wait for client send  
		    	String s = new String(buf, 0, length);
		        
		    	if(s.charAt(0) == 'R') {					//if input the user name and password to register
		    		Registertask(s);
		    	}else if(s.charAt(0) == 'L') {					//if input the user name and password to login
		    		logintask(s);
		    	}else {
		    		compare(s);
		    	}
		    	
		        length = reader.read(buf);
		    } 
		    
		    out.close(); 
		    socket.close(); 

		} catch (IOException e) { 
		    e.printStackTrace(); 
		} 
	}
	public static void logintask(String s) throws IOException {//check the correct of Account 
		s = s.substring(1);
		String[] cuttedfromclient = s.split(" ");
		if(map.containsKey(cuttedfromclient[0])) {			//there is the certain account in txt
			if(map.get(cuttedfromclient[0]).equals(cuttedfromclient[1])){//the password is true
				out.write("loginsuccess");
		        out.flush();
			}
		}else {
			
			out.write("nothisaccount");
	        out.flush();
		}
		
	}
	public static void Registertask(String S) throws IOException {//check the correct of Account 
		S = S.substring(1);
		String[] cuttedfromclient = S.split(" ");
		if(map.containsKey(cuttedfromclient[0])) {			//if there is the certain account in txt
			if(map.get(cuttedfromclient[0]).equals(cuttedfromclient[1])){//the password is true
				out.write("alreadyregister");
		        out.flush();
			}
		}else {//give it to txt and store
			String UP = new String();

			map.put(cuttedfromclient[0], cuttedfromclient[1]);
			UP = "R" + cuttedfromclient[0] + " " + cuttedfromclient[1]+ "\r\n";//get it to the type fit for database
			File writename = new File("C:\\Users\\wangyucheng\\Desktop\\Account.txt"); // 相對路徑，如果沒有則要建立一個新的output。txt檔案
			writename.createNewFile(); // 建立新檔案
			BufferedWriter out = new BufferedWriter(new FileWriter(writename, true));
			out.write(UP);
			out.flush();
			out.close();
			
		}
		
	}
	public void compare(String S) throws IOException {
		BufferedWriter outscore = null;
		BufferedWriter outscore1 = null;
		
		if(counter==1) {
			back = Integer.parseInt(S);
			
				for(Socket sc : sockets) {// send to every client
					outscore = new BufferedWriter 
							(new OutputStreamWriter
									(sockets.get(0).getOutputStream()));
					outscore1 = new BufferedWriter 
							(new OutputStreamWriter
									(sockets.get(1).getOutputStream()));
					
					if(back > front) {//if back is large than front
						outscore.write("lose");
						outscore.flush();
						outscore1.write("win");
						outscore1.flush();
					}else if(back < front) {//positive
						outscore.write("win");
						outscore.flush();
						outscore1.write("lose");
						outscore1.flush();
					}else {//same
						outscore.write("samescore");
						outscore.flush();
						outscore1.write("samescore");
						outscore1.flush();
					}
					counter = 0;
				} 
			
		}else {
			front = Integer.parseInt(S);
			counter++;
		}
	}
}
}
