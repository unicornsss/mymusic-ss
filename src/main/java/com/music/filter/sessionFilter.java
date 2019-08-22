package com.music.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by 羊 on 2018/10/31.
 */
public class sessionFilter implements Filter {
    // 日志
    Logger logger = LoggerFactory.getLogger(sessionFilter.class);


    public void destroy() {
// TODO Auto-generated method stub
    }


    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain)
            throws IOException, ServletException {
        if (!(sreq instanceof HttpServletRequest) || !(sresp instanceof HttpServletResponse)) {
            throw new ServletException("OncePerRequestFilter just supports HTTP requests");
        }
        HttpServletRequest httpRequest = (HttpServletRequest) sreq;
        HttpServletResponse httpResponse = (HttpServletResponse) sresp;
        httpResponse.setHeader("Cache-Control","no-cache");
        httpResponse.setHeader("Pragma","no-cache");
        httpResponse.setDateHeader ("Expires", -1);
        httpResponse.setHeader("P3P","CP=CAO PSA OUR");

        HttpSession session = httpRequest.getSession();
        if(!httpResponse.isCommitted()){
            if(session != null){
                Object object = session.getAttribute("adminSession");
                String userName = object == null ? null : (String) object;
                if (userName == null) {
                    boolean isAjaxRequest = isAjaxRequest(httpRequest);
                    if (isAjaxRequest) {
                        httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
                    }
                    httpResponse.sendRedirect("admin_login");
                }
            }else{
                httpResponse.sendRedirect("admin_login");
            }
        }
        chain.doFilter(sreq, sresp);
    }


    /**
     * 判断是否为Ajax请求
     * 
     * @param request
     *            HttpServletRequest
     * @return 是true, 否false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    public void init(FilterConfig config) throws ServletException {
    }
}

