package car;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageDeal extends Thread {
    private JSONArray message;
    private ArrayList<ConnctThread> threads;
    private HashMap<Integer, Socket> socks;
    public MessageDeal()
    {
        socks=new HashMap<>();
        message=new JSONArray();
        threads=new ArrayList<>();
    }
    public int add_socks(int id,Socket sock)
    {
        socks.put(id,sock);
        return socks.size();
    }
    public int add_thread(ConnctThread connctThread)
    {
        threads.add(connctThread);
        return threads.size();
    }

    @Override
    public void run() {
        System.out.println("message dealer start to run");
        while (true)
        {
            for (ConnctThread thread:
                 threads) {
                if (thread.getMessagese().size()>0)
                {
                    System.out.println("|");
                }
            }
        }
    }
}
