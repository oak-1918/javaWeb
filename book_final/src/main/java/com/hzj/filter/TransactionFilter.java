package com.hzj.filter;

import com.hzj.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author hzj
 * @create 2022-03-04 12:00
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e); //将异常抛给Tomcat
        }
    }

    @Override
    public void destroy() {

    }
}