package ekgo.study.spring.testSrc;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dhjung on 2017. 7. 13..
 */
@WebServlet(name= "MySerlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello solunar".getBytes());
        out.flush();
        out.close();
    }
}
