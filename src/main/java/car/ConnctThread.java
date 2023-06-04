package car;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnctThread extends Thread{
    private Socket sock;

    public ArrayList<String> getMessagese() {
        return messagese;
    }

    private ArrayList<String> messagese;
    private int rid;
    public int get_Id() {
        return rid;
    }


    public ConnctThread(Socket s)
    {
        sock=s;
        messagese=new ArrayList<>();
    }
    @Override
    public void run() {
        try {
        // Get the input stream from the client
        InputStream inputStream = sock.getInputStream();

        // Create a buffer to store the read data
        byte[] buffer = new byte[1024];
        while (true)
        {
            int bytesRead = inputStream.read(buffer);

            if (bytesRead != -1) {
                // Convert the byte array to a string and print it
                String receivedData = new String(buffer, 0, bytesRead);
                System.out.println("Received data: " + receivedData);
                JSONObject mess=new JSONObject();
                mess.put("from",rid);
                mess.put("mess",receivedData);
                messagese.add(mess);
            }
        } }catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Read data into the buffer and return the actual number of bytes read

}

