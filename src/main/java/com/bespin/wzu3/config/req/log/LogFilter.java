package com.bespin.wzu3.config.req.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;
@WebFilter(urlPatterns = "/*", filterName = "logFilter")
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        printRequest(requestWrapper);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void printRequest(ServletRequest servletRequest) {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        String requestBody = "";
        String requestContentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (requestContentType != null) {

            //	只拦截 json 请求的参数
            if (requestContentType.startsWith(MediaType.APPLICATION_JSON_VALUE) || requestContentType.startsWith(MediaType.APPLICATION_XML_VALUE)) {

                requestBody = getRequestBody(request);
                // anonymousUser 匿名用户
                log.info("user ==={}", SecurityContextHolder.getContext().getAuthentication().getName());
                log.info("url ==={}", uri);
                log.info("requestBody ==={}", requestBody);
            }
        }
    }

    private String getRequestBody(HttpServletRequest request) {

        int contentLength = request.getContentLength();
        if (contentLength <= 0) {

            return "";
        }
        try {

            return request.getReader().lines().collect(Collectors.joining());
        } catch (IOException e) {

            log.error("获取请求体失败", e);
            return "";
        }
    }

    @Override
    public void destroy() {

    }

}