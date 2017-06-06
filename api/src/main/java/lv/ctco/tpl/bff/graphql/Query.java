package lv.ctco.tpl.bff.graphql;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLField;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;
import lv.ctco.tpl.bff.graphql.resolvers.JokeResolver;
import lv.ctco.tpl.bff.graphql.types.Joke;
import org.springframework.beans.factory.annotation.Autowired;

@GraphQLObject
public class Query {

    @Autowired JokeResolver jokeResolver;

    @GraphQLField
    public Joke joke() {
        return jokeResolver.getJoke();
    }
}
