import car.Sock2;
import car.TcpPart;

import java.io.IOException;

public class tcpPartTest {
    public static void main(String[] args)  {
        Sock2 sock2=new Sock2();
        sock2.waitSlave();
        sock2.send_meassage("hello");
    }
}
