package demo.spr.snpass;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ErrorPage {

    private ErrorPage() {}

    public static void serve(final HttpServletRequest request, final HttpServletResponse response)
                                                                                                   throws IOException {
        response.setContentType("text/plain; charset=UTF-8");
        serve(request, /* response, */ response.getWriter());
    }

    @SuppressWarnings("rawtypes")
    private static void serve(
            final HttpServletRequest request,
            /* final HttpServletResponse response, */
            final Writer out) {

        try {
            out.write("statusCode: ");
            out.write(String.valueOf(request.getAttribute("javax.servlet.error.status_code")));
            out.write("\n\nour host name: ");
            out.write(java.net.InetAddress.getLocalHost().getHostName());
            out.write("\nServerName: ");
            out.write(request.getServerName());
            out.write("\nLocalName: ");
            out.write(request.getLocalName());
            out.write("\nLocalPort: ");
            out.write(String.valueOf(request.getLocalPort()));
            out.write("\nRemoteHost: ");
            out.write(request.getRemoteHost());
            out.write("\nRemotePort: ");
            out.write(String.valueOf(request.getRemotePort()));
            out.write("\nisSecure: ");
            out.write(String.valueOf(request.isSecure()));
            out.write("\nscheme: ");
            out.write(request.getScheme());
            out.write("\nrequestMethod: ");
            out.write(request.getMethod());
            out.write("\nrequestURL: ");
            out.write(String.valueOf(request.getRequestURL()));
            out.write("\nrequestURI: ");
            out.write(request.getRequestURI());
            out.write("\nqueryString: ");
            request.getQueryString();

            out.write("\norig_request_uri: ");
            out.write(String.valueOf(request.getAttribute("javax.servlet.forward.request_uri")));

            out.write("\norig_query_string: ");
            out.write(String.valueOf(request.getAttribute("javax.servlet.forward.query_string")));

            out.write("\nexception: ");
            out.write(String.valueOf(request.getAttribute("javax.servlet.error.exception")));
            out.write("\n=============================================");
            if ("".length() == 10 && request.getCookies() != null) {
                for (final Cookie cookie : request.getCookies()) {
                    final String cookieVal = cookie.getValue();
                    final String cookieName = cookie.getName();
                    out.write("\n");
                    out.write(cookieName);
                    out.write(": ");
                    out.write(cookieVal);
                }
            }
            out.write("\n=============================================");
            final Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                final String headerName = (String) headerNames.nextElement();
                final Enumeration headerVals = request.getHeaders(headerName);
                while (!("cookie".equalsIgnoreCase(headerName) || "authorization".equalsIgnoreCase(
                    headerName)) && headerVals.hasMoreElements()) {
                    final String headerVal = (String) headerVals.nextElement();
                    out.write("\n");
                    out.write(headerName);
                    out.write(": ");
                    out.write(headerVal);
                }
            }
            out.write("\n=============================================\n");
        } catch (final Exception e) {
            LOGGER.error("", e);
            e.printStackTrace(new java.io.PrintWriter(out) {

                @Override
                public void println() {
                    write('\n');
                }
            });
        }

    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPage.class);
}
