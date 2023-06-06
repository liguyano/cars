import car.Sock2;

import java.io.IOException;

public class tcpPartTest {
    public static void main(String[] args)  {
        Sock2 sock2=new Sock2();
        sock2.waitSlave();
        sock2.send_meassage("hello");
        while (true)
        {
            String so=sock2.get_message();
            if (so.equals(""))
            {

            }else
            {

            }
        }
    }
}
