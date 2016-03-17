package net.chuiev.selcommittee;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Filter, that defines encoding of client request.
 *
 * @author Oleksii Chuiev
 *
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class EncodingFilter implements Filter{
    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    private String code;

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.debug("Start initializing filter: "
                + EncodingFilter.class.getSimpleName());
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        LOG.debug("Using the filter " + EncodingFilter.class.getSimpleName());

        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);

        LOG.debug("Filter used successfully " + EncodingFilter.class.getSimpleName());
    }

    public void destroy() {
        LOG.debug("Start destroying filter: "
                + EncodingFilter.class.getSimpleName());
        code = null;
    }
}
