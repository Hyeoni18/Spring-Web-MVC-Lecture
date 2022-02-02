package me.whiteship;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebApplication implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //이 애플리케이션 컨텍스트에
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(servletContext);
        context.register(WebConfig.class); //bean 설정을 등록. WebConfig 사용할거야.
        context.refresh();

        //애플리케이션 컨텍스트는 디스패처 서블릿을 만들어야지.
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context); //여기에 위에서 만든것을 주면 돼.
        //그리고 등록해야지. 이름을 정하고, 서블릿을 넣어준다.
        ServletRegistration.Dynamic app = servletContext.addServlet("app", dispatcherServlet);
        //매핑. app 이하의 모든 요청을 모두 디스패처 서블릿이 받도록.
        app.addMapping("/app/*");
    }
}
