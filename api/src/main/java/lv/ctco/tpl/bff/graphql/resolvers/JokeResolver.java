package lv.ctco.tpl.bff.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;
import lv.ctco.tpl.bff.graphql.types.JokeCategoryInput;
import lv.ctco.tpl.bff.integration.icndb.ICNDB;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeResponseModel;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeValueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JokeResolver implements GraphQLResolver<JokeQuery> {

    @Autowired
    private ICNDB icndb;

    public Joke getJokeByCategory(JokeQuery root, JokeCategoryInput request) {
        JokeCategory category = request.getCategory();
        JokeResponseModel response = category != null ? icndb.getRandomJokeByCategory(category)
            : icndb.getRandomJoke();
        JokeValueModel jokeValue = response.getValue();
        return mapToJoke(jokeValue);
    }

    public Joke getJokeById(JokeQuery root, String id) {
        JokeResponseModel response = icndb.getJokeById(id);
        JokeValueModel jokeValue = response.getValue();
        return mapToJoke(jokeValue);
    }

    private Joke mapToJoke(JokeValueModel jokeValue) {
        return new Joke(
            jokeValue.getJoke(),
            jokeValue.getId(),
            jokeValue.getCategories().stream()
                .map(String::toUpperCase)
                .map(JokeCategory::valueOf)
                .collect(Collectors.toList())
        );
    }
}
