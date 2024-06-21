package uk.zwfield.jx3bot.filter;

/**
 * @author Field
 * @date 2024-06-20 14:08
 **/
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import uk.zwfield.jx3bot.utils.SnowflakeIdUtil;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Field
 **/
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class RequestPrintFilter implements Filter {

    private static final ThreadLocal<Long> ENTRY_TIME = new ThreadLocal<>();

    private static final String[] REQUEST_METHOD = new String[]{"GET", "POST", "PUT", "DELETE"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            MDC.put("traceId", httpRequest.getHeader("traceId"));
            MDC.put("spanId", String.valueOf(SnowflakeIdUtil.getUniqueInstance().nextId()));
            if (Arrays.asList(REQUEST_METHOD).contains(httpRequest.getMethod())) {
                log.info("=========Start==========");
                log.info("请求URL: {}", httpRequest.getRequestURI());
                log.info("Method:{}", httpRequest.getMethod());
                log.info("QueryParam:{}", JSON.toJSONString(request.getParameterMap()));
                //存入请求中
                ENTRY_TIME.set(System.currentTimeMillis());
                log.info("接口日志: ");
                chain.doFilter(request, response);
                //打印处理时间
                log.info("请求处理时间: {}ms", System.currentTimeMillis() - ENTRY_TIME.get());
                log.info("======================");
            } else {
                chain.doFilter(request, response);
            }
        } finally {
            ENTRY_TIME.remove();
            MDC.clear();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

