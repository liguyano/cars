package car;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ConnctThread implements Runnable{
    private Socket sock;
    private JSONArray messagese;
    private int id;
    public ConnctThread(HashMap<Integer, Socket> socks,int id,JSONArray messages)
    {
        this.id=id;
        sock=socks.get(this.id);
        messagese=messages;
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
                mess.put("from",id);
                mess.put("mess",receivedData);
                messagese.add(mess);
            }
        } }catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Read data into the buffer and return the actual number of bytes read

}

