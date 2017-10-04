package lv.ctco.tpl.bff.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lv.ctco.tpl.bff.graphql.resolvers.JokeQuery;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    public String ping() {
        return "pong";
    }

    public JokeQuery jokes() {
        return new JokeQuery();
    }
}
