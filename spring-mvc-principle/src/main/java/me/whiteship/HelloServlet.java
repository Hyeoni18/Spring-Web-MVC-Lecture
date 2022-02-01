package me.whiteship;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        //ContextLoaderListener 가 ApplicationContext 를 등록해주는데 어디에 등록해주냐면, 서블릿 컨텍스트 (모든 서블릿이 사용할 수 있는 공용 저장소 느낌) 에 등록해줘. 어떤 이름으로 해주냐면. 찾아가보면 servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context); 라는 이름으로 컨텍스트를 등록해놨어. 저 이름으로 꺼내 쓰면 돼.
        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        HelloService helloService = context.getBean(HelloService.class);    //여기서 getBean 으로 HelloService 를 꺼내오는거야.
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h1>Hello, " + helloService.getName() + "</h1>"); //그러면 helloService 에서 getName 을 사용해서 이름을 가져오는거지.
        //helloService 를 쓰고 있지만, 직접 new 해서 쓰는게 아니라 스프링이 제공해주는 IoC 컨테이너에 들어있는 bean 을 꺼내다 쓰고 있는거야. 그러니까 우리가 bean 만 교체하면 코드는 손대지 않아도 helloService 를 다른 bean 으로 교체해서 쓸 수도 있을거야.
        resp.getWriter().println("</body>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("</html>");
    }

    public Object getName() {
        return getServletContext().getAttribute("name");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
