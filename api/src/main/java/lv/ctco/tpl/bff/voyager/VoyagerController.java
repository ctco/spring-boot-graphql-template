package lv.ctco.tpl.bff.voyager;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class VoyagerController {

    @RequestMapping(value = "/voyager")
    public void voyager(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        InputStream inputStream = new ClassPathResource("voyager.html").getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();

        IOUtils.copy(inputStream, outputStream);
    }
}
