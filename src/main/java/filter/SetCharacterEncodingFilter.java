package filter;



import car.Sock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(urlPatterns = "/*", filterName = "rest0PubFilter")
public class SetCharacterEncodingFilter implements Filter{
    public static final Logger logger = LogManager.getLogger("main filter");
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info(request.getRemoteAddr()+" assessed");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
        response.setCharacterEncoding("utf-8"); // 指定输出到客户端的是编码格式
        response.setContentType("text/html;charset=UTF-8"); // 指定浏览器解析数据的时候，使用的编码格式
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }



}
