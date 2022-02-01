package me.whiteship;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter Init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter");
        chain.doFilter(request, response);  //두 필터 안에서 메세지만 표출하면 다음 필터로 전달이 안돼. 필터 체인에 doFilter 로 연결을 해줘야 해. 마지막은 알아서 서블릿으로 연결 돼.
    }

    @Override
    public void destroy() {
        System.out.println("Filter Destroy");
    }
}
