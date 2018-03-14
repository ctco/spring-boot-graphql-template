package lv.ctco.tpl.bff.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lv.ctco.tpl.bff.graphql.models.JokeModels;
import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.graphql.types.JokeCategoryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JokeResolvers implements GraphQLResolver<JokeQuery> {

    @Autowired
    private JokeModels jokeModels;

    public Joke getRandomJokeLimitedToCategory(JokeQuery root, JokeCategoryInput request) {
        return jokeModels.getRandomJokeLimitedToCategory(request);
    }

    public Joke getJokeById(JokeQuery root, String id) {
        return jokeModels.getJokeById(id);
    }

    public Joke getRandomJoke(JokeQuery root) {
        return jokeModels.getRandomJoke();
    }
}
