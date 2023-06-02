package car;
import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class TcpPart {
    private int port;
    private HashMap<Integer,Socket> socks;
    private int nextId = 1;
    JSONArray messages;
    private void init()
    {
        socks=new HashMap<>();
        messages=new JSONArray();
    }
    public TcpPart(int port)
    {
        this.port=port;
        init();

    }
    public TcpPart()
    {
        port=7001;
        init();
    }
    public void start()
    {
        try {
            new Thread(new MessageDeal(messages,socks)).start();
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started, listening on port " + port);
            while (true)
            {
                System.out.println("id"+nextId);
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                socks.put(nextId,clientSocket);
                new Thread(new ConnctThread(socks,nextId++,messages)).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        int port = 7001;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started, listening on port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Get the input stream from the client
                InputStream inputStream = clientSocket.getInputStream();

                // Create a buffer to store the read data
                byte[] buffer = new byte[1024];

                // Read data into the buffer and return the actual number of bytes read
                int bytesRead = inputStream.read(buffer);

                if (bytesRead != -1) {
                    // Convert the byte array to a string and print it
                    String receivedData = new String(buffer, 0, bytesRead);
                    System.out.println("Received data: " + receivedData);

                    // Send confirmation message to the client
                    OutputStream outputStream = clientSocket.getOutputStream();
                    String confirmationMessage = "received";
                    outputStream.write(confirmationMessage.getBytes());
                    outputStream.flush();
                    System.out.println("Confirmation message sent: " + confirmationMessage);
                }

                // Close the connection with the client
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

