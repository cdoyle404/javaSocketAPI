package networkProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String Username;
	
	public Client(Socket socket, String Username) {
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.Username = Username;
			
		} catch(IOException e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
			
		}
	}
	public void sendMessage() {
		try {
			bufferedWriter.write(Username);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			Scanner scanner = new Scanner(System.in);
			while (socket.isConnected()) {
				String messageToSend = scanner.nextLine();
				
				 if (messageToSend.equals("/q")) {
	                    bufferedWriter.write(Username + " has left the chat.");
	                    bufferedWriter.newLine();
	                    bufferedWriter.flush();
	                    closeEverything(socket, bufferedReader, bufferedWriter); // Disconnect from the server
	                    break; // Exit the loop
	                }
				
				bufferedWriter.write(Username + ": " + messageToSend);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
		} catch (IOException e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
			
		}
	}
	public void ListenForMessage() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String msgFromGC;
				
				while (socket.isConnected()) {
					try {
						msgFromGC = bufferedReader.readLine();
						System.out.println(msgFromGC);
						
					} catch(IOException e) {
						closeEverything(socket, bufferedReader, bufferedWriter);
						
					}
				}
			}
		}).start();
		
		}
	
	
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
			if (socket != null) {
				socket.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the username for the groupchat: ");
		String Username = scanner.nextLine();
		Socket socket = new Socket("localhost", 1030);
		Client client = new Client(socket, Username);
		client.ListenForMessage();
		client.sendMessage();
	}
	
	}

