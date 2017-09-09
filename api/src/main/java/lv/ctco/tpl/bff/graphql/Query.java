package lv.ctco.tpl.bff.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lv.ctco.tpl.bff.graphql.resolvers.JokeResolver;
import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;
import lv.ctco.tpl.bff.graphql.types.JokeCategoryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private JokeResolver jokeResolver;

    public Joke jokeByCategory(JokeCategoryInput input) {
        return jokeResolver.getJokeByCategory(input.getCategory());
    }

    public Joke jokeById(String id) {
        return jokeResolver.getJokeById(id);
    }
}
