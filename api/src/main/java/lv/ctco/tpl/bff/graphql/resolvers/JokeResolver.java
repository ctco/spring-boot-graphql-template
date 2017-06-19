package lv.ctco.tpl.bff.graphql.resolvers;

import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;
import lv.ctco.tpl.bff.integration.icndb.ICNDB;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeResponseModel;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeValueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class JokeResolver {

    @Autowired
    private ICNDB icndb;

    public Joke getJokeByCategory(JokeCategory category) {
        JokeResponseModel response = category != null ? icndb.getRandomJokeByCategory(category)
                                                      : icndb.getRandomJoke();
        JokeValueModel jokeValue = response.getValue();
        return mapToJoke(jokeValue);
    }

    public Joke getJokeById(String id) {
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
