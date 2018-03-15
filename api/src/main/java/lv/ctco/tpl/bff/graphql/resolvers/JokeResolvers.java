package lv.ctco.tpl.bff.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lv.ctco.tpl.bff.graphql.models.JokeModel;
import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.graphql.types.JokeCategoryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JokeResolvers implements GraphQLResolver<JokeQuery> {

    @Autowired
    private JokeModel jokeModel;

    public Joke getRandomJokeLimitedToCategory(JokeQuery root, JokeCategoryInput request) {
        return jokeModel.getRandomJokeLimitedToCategory(request);
    }

    public Joke getJokeById(JokeQuery root, String id) {
        return jokeModel.getJokeById(id);
    }

    public Joke getRandomJoke(JokeQuery root) {
        return jokeModel.getRandomJoke();
    }
}
