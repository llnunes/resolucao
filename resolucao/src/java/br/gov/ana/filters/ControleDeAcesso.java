/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.ana.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas.nunes
 */
@WebFilter(servletNames = {"Faces Servlet"})
public class ControleDeAcesso implements Filter {

    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    /**
     *
     */
    public ControleDeAcesso() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("ControleDeAcesso:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         * for (Enumeration en = request.getParameterNames();
         * en.hasMoreElements(); ) { String name = (String)en.nextElement();
         * String values[] = request.getParameterValues(name); int n =
         * values.length; StringBuffer buf = new StringBuffer();
         * buf.append(name); buf.append("="); for(int i=0; i < n; i++) {
         * buf.append(values[i]); if (i < n-1) buf.append(","); }
         * log(buf.toString()); }
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         * for (Enumeration en = request.getAttributeNames();
         * en.hasMoreElements(); ) { String name = (String)en.nextElement();
         * Object value = request.getAttribute(name); log("attribute: " + name +
         * "=" + value.toString());
         *
         * }
         */
        // For example, a filter might append something to the response.
	/*
         * PrintWriter respOut = new PrintWriter(response.getWriter());
         * respOut.println("<P><B>This has been appended by an intrusive
         * filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        /*request.setCharacterEncoding("UTF-8");

         response.setCharacterEncoding("UTF-8");     */
        //((HttpServletResponse) response).setHeader("X-UA-Compatible", "IE=9");

        /*if (debug) {
         log("ControleDeAcesso:doFilter() " + request.getLocalAddr() + " " + request.getLocalName() + " " + request.getContentType() + " " + request.getProtocol() + " " + request.getCharacterEncoding() + " " + response.getCharacterEncoding() + " " + request.getScheme() + " " + request.getServerName() + " " + request.getRemoteAddr() + " " + request.getRemoteHost());
         }*/
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //.endsWith("theme.css.xhtml") || httpRequest.getRequestURI().endsWith("jquery.js.xhtml") || httpRequest.getRequestURI().endsWith("theme.css.xhtml") || httpRequest.getRequestURI().endsWith("jsfcrud.css.xhtml")
        try {
            if (session.getAttribute("usuario") != null
                    || httpRequest.getRequestURI().endsWith("login.xhtml")
                    || httpRequest.getRequestURI().contains("javax.faces.resource")
                    || httpRequest.getRequestURI().endsWith("versao.xhtml")
                    || httpRequest.getRequestURI().endsWith("ultimosDados.xhtml")) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect("login.xhtml");
            }
        } catch (Exception e) {
            session.removeAttribute("usuario");
            /*log(e.getMessage());
            e.printStackTrace();*/
            httpResponse.sendRedirect("/samse/login.xhtml");
        }

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ControleDeAcesso()");
        }
        StringBuilder sb = new StringBuilder("ControleDeAcesso(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.isEmpty()) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     *
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    /**
     *
     * @param msg
     */
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
