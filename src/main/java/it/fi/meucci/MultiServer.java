package it.fi.meucci;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer 
{
    public void KickOff()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(7777);
            
            for(;;)
            {
                System.out.println("server still waiting");
                Socket socket = serverSocket.accept();
                System.out.println("server socket" + socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("error during instance");
            System.exit(1);
        }
        
    }
}
