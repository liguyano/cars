package car;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.Socket;
import java.util.HashMap;

public class MessageDeal implements Runnable {
    private JSONArray message;
    private HashMap<Integer, Socket> socks;
    public MessageDeal(JSONArray message,HashMap<Integer, Socket> socks)
    {
        this.message=message;
        this.socks=socks;
    }

    @Override
    public void run() {
        System.out.println("message dealer start to run");
        while (true)
        {
            if (message.size()>0)
            {
                JSONObject jo= (JSONObject) message.get(1);
                System.out.println(jo.get("mess"));
            }
        }
    }
}
