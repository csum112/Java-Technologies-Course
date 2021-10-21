package ro.uaic.info.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "RequestLogger")
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final String addr = servletRequest.getRemoteAddr();
        log.info(String.format("Received a request from %s", addr));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
