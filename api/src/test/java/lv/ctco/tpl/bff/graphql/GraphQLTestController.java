package lv.ctco.tpl.bff.graphql;

import graphql.servlet.GraphQLServlet;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GraphQLTestController {

    @Autowired
    private GraphQLServlet graphQLServlet;

    @RequestMapping(value = "${graphql.servlet.mapping:/graphql}")
    @SneakyThrows
    public void service(HttpServletRequest request, HttpServletResponse response) {
        graphQLServlet.service(request, response);
    }
}
