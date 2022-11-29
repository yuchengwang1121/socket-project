import java.net.*; 
import java.io.*; 

public class thread extends Thread { 
private Socket socket = null; 

public thread(Socket socket) { 
super("GMultiServerThread"); 
this.socket = socket; 						//in socket give to this class 
} 

public void run() { 
try { 
    BufferedWriter out = new BufferedWriter (new OutputStreamWriter(socket.getOutputStream())); //create new print writer from current Output stream of socket
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));// read from socket
    
    char[] buf = new char[1024];
    int length = reader.read(buf);
    while (length > 0) {					//wait for client send  
        length = reader.read(buf);
    } 
    
    out.close(); 
    socket.close(); 

} catch (IOException e) { 
    e.printStackTrace(); 
} 
} 
} 
