package it.fi.meucci;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer 
{
    static ArrayList <Socket> Lista_s = new ArrayList<Socket>();
    static ArrayList <Thread> Lista_t = new ArrayList<Thread>();
    static ServerSocket serverSocket;
    public void kickOff()
    {
        try
        {
            serverSocket = new ServerSocket(7777);
            
            for(;;)
            {
                System.out.println("server still waiting");
                Socket socket = serverSocket.accept();
                Lista_s.add(socket);
                System.out.println("server socket" + socket);
                ServerThread serverThread = new ServerThread(socket);
                Lista_t.add(serverThread);
                serverThread.start();
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("error during instance");
        }
        
    }

    public static void spegni()
    {
        for(int i = 0; i < Lista_s.size(); i++)
        {
            try 
            {
                Lista_s.get(i).close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }    

        for(int i = 0; i < Lista_t.size(); i++)
        {
            
            Lista_t.get(i).interrupt();
            
        }

        try 
        {
            serverSocket.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
