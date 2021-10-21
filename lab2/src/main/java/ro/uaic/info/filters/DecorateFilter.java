package ro.uaic.info.filters;

import lombok.extern.slf4j.Slf4j;
import ro.uaic.info.util.CharResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;

@Slf4j
@WebFilter(urlPatterns = "/*", filterName = "RequestDecorator")
public class DecorateFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Decorating response");
        CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, wrapper);

        String originalContent = wrapper.toString();
        Writer writer = new CharArrayWriter();
        writer.write("<h1>HEADER</h1>");
        writer.write(originalContent);
        writer.write("<h1>FOOTER</h1>");

        String alteredContent = writer.toString();
        servletResponse.setContentLength(alteredContent.length());
        servletResponse.getWriter().write(alteredContent);
    }
}
