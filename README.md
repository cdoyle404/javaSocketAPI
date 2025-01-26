ReadME
Network Technologies

Network Programming Project

 

Description:
This Java Project, is a command line tool that allows users to generate a server and connect multiple chats to it. In this project you can add multiple clients to this network based chat application using the Java Socket API. We have achieved a multiple chat through the use of Threads.
To achieve this, we have three classes in use: Client, ClientHandler and ServerMultiChat.
I have developed these in a package and thus the running instructions are labelled below.

Running Manual:
Open your terminal window and move to the ‘src’ file directory. Following this you will need to compile the code before running.
cd path/to/src/file


To compile use the following commands from the ‘src’ directory:
javac networkProgramming/ServerMultichat.java
javac networkProgramming/Client.java

 

To run, open 3 or more windows. The Server must be the first window opened and only then can the Clients be opened from there after:
java networkProgramming/ServerMultichat

java networkProgramming/Client


Enter the name for each of the clients as prompted. From this point on there will be a multi-way live-chat between all of the client windows which you have opened.
To leave you can type “/q”, and this chat will be removed from the Server.
