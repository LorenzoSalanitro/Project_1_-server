package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String received = null;
    String modified = null;
    BufferedReader input;
    DataOutputStream output;


public Socket waiting()
{
    try{
        System.out.println("Server start execution");
        server = new ServerSocket(7777);
        client = server.accept();
        server.close();
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new DataOutputStream(client.getOutputStream());
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Error during server instance");
        System.exit(1);
    }
    return client;

}
public void comunicate()
{
    try{
        System.out.println("Welcome client, write a phrase");
        received = input.readLine();
        modified = received.toUpperCase();
        System.out.println("Printing");
        output.writeBytes(modified + '\n');
        System.out.println("SERVER: elaboration ended");
        client.close();
    }
    catch(Exception e)
    {

    }
}

}
