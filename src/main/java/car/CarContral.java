package car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "car1",value = "/car")
public class CarContral extends HttpServlet {
    Sock2 sock;
    @Override
    public void init() throws ServletException {
        sock=new Sock2();
        sock.waitSlave();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
