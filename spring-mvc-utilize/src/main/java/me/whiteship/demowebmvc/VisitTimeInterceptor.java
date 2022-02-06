package me.whiteship.demowebmvc;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

//웹 사이트에 접속한 시간을 기록하는 인터셉터
public class VisitTimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("visitTime") == null) {
            session.setAttribute("visitTime", LocalDateTime.now());
        }
        return true; //true 여야 다음 인터셉터나 핸들러로 전달.
    }
}
