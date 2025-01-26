package networkProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerMultichat {

	private ServerSocket serverSocket;
	
	public ServerMultichat(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void startServerMultichat() {
		
		try {
			
			while(!serverSocket.isClosed()) {
				 Socket connection = serverSocket.accept();
                 System.out.println("Client connected from " + connection.getInetAddress() + ":" + connection.getPort());
                 ClientHandler clientHandler = new ClientHandler(connection);  //Line output needs to change
                 
                 Thread task = new Thread(clientHandler);
                 task.start();
			}  
             } catch (IOException e) {
             }
		}
	
			
		public void closeServerSocket() {
			try {
				if(serverSocket != null) {
					serverSocket.close();
				}
		} catch (IOException e) {
            System.err.println("Couldn't start server");
		}
			
	}
		public static void main(String[] args) throws IOException {
			ServerSocket serverSocket = new ServerSocket(1030);
			ServerMultichat server = new ServerMultichat(serverSocket);
			server.startServerMultichat();

}
}

