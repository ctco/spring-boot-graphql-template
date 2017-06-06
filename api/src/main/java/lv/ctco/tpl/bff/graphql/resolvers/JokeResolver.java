package lv.ctco.tpl.bff.graphql.resolvers;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLField;
import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.integration.icndb.ICNDB;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeResponseModel;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeValueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeResolver {

    @Autowired ICNDB icndb;

    @GraphQLField
    public Joke getJoke() {
        JokeResponseModel response = icndb.getRandomJoke();
        JokeValueModel jokeValue = response.getValue();
        return new Joke(jokeValue.getJoke());
    }
}
