package demo.spr.snpass;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, produces = "text/html")
    public void error(final HttpServletRequest request, final HttpServletResponse response)
                                                                                            throws IOException {
        ErrorPage.serve(request, response);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
