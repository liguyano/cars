package car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Sock2 {
    private Socket sock;
    private int port=7001;
    private String password="wwpdsg";
    private int connect_statues;
    /*
    * not connect 0
    * connected 1
    *
    * */
    public int getConnect_statues() {
        return connect_statues;
    }

    public static final Logger log = LogManager.getLogger(Sock2.class);
    private ConnctThread connctThread;
    public Sock2()
    {
        connect_statues=0;

    }
    public Sock2(int port)
    {
        this.port=port;
        connect_statues=0;
    }
    public void send_meassage(String me)  {
        try {
            sock.getOutputStream().write(me.getBytes());
        } catch (IOException e) {
            log.error("when send "+me);
            log.error(e);
        }
    }
    public String get_message()
    {
        return connctThread.getAmessage();
    }
    public void waitSlave()
    {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            log.info("Server started, listening on port " + port);
            while (true)
            {
                sock=serverSocket.accept();
                byte[] by=new byte[50];
                int rd=sock.getInputStream().read(by);
                if (rd!=-1)
                {
                    String receivedData = new String(by, 0, rd);
                    log.info(receivedData);
                    if (receivedData.equals(password))
                    {
                        log.info("right");
                        connect_statues=1;
                        connctThread=new ConnctThread(sock);
                        connctThread.start();
                        break;
                    }else {
                        sock.close();
                        connect_statues=0;
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            log.error(e);
        }
    }
}
