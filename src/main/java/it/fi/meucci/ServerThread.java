package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread 
{
    ServerSocket server = null;
    Socket client = null;
    String stringRecived = null;
    String stringModified = null;
    BufferedReader inputFromClient;
    DataOutputStream outputToClient;

    public ServerThread (Socket socket)
    {
        this.client = socket;
    }

    public void run()
    {
        try
        {
            comunicate();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void comunicate() throws Exception
    {
        inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outputToClient = new DataOutputStream(client.getOutputStream());

        for(;;)
        {
            stringRecived = inputFromClient.readLine();
            if(stringRecived == null || stringRecived.equals("fine"))
            {
                outputToClient.writeBytes(stringRecived + "server closing" + '\n');
                System.out.println("Echo on server closing: " + stringRecived);
                break;
            }
            else if(stringRecived.equals("CLOSE"))
            {
                MultiServer.spegni();
                server.close();
                break;
            }
            else
            {
                outputToClient.writeBytes("received: " + stringRecived + '\n');
                System.out.println("Echo on server: " + stringRecived);
            }
        }

        outputToClient.close();
        inputFromClient.close();

        System.out.println("closing socket" + client);
        client.close();
    }
    
}
