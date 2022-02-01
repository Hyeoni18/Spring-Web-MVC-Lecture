package me.whiteship;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) { //컨텍스트가 초기화 되었다.
        System.out.println("Context Initialized");
        sce.getServletContext().setAttribute("name", "hello");  //애트리뷰트를 추가할 수 있어.
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { //컨텍스트 종료될 때.
        System.out.println("Context Destroyed");
    }
}
