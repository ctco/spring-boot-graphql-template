package lv.ctco.tpl.bff.graphql.models;

import lv.ctco.tpl.bff.connectors.icndb.ICNDB;
import lv.ctco.tpl.bff.connectors.icndb.ICNDBJoke;
import lv.ctco.tpl.bff.connectors.icndb.ICNDBJokeEnvelop;
import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;
import lv.ctco.tpl.bff.graphql.types.JokeCategoryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JokeModels {

    @Autowired
    private ICNDB icndb;

    public Joke getRandomJokeLimitedToCategory(JokeCategoryInput request) {
        JokeCategory category = request.getCategory();
        ICNDBJokeEnvelop icndbJokeEnvelop = category != null ? icndb.getRandomJokeLimitedToCategory(category)
            : icndb.getRandomJoke();
        ICNDBJoke icndbJoke = icndbJokeEnvelop.getValue();
        return mapToJoke(icndbJoke);
    }

    public Joke getJokeById(String id) {
        ICNDBJokeEnvelop icndbJokeEnvelop = icndb.getJokeById(id);
        ICNDBJoke icndbJoke = icndbJokeEnvelop.getValue();
        return mapToJoke(icndbJoke);
    }

    public Joke getRandomJoke() {
        ICNDBJokeEnvelop icndbJokeEnvelop = icndb.getRandomJoke();
        ICNDBJoke icndbJoke = icndbJokeEnvelop.getValue();
        return mapToJoke(icndbJoke);
    }

    private Joke mapToJoke(ICNDBJoke icndbJoke) {
        return new Joke(
            icndbJoke.getJoke(),
            icndbJoke.getId(),
            icndbJoke.getCategories().stream()
                .map(String::toUpperCase)
                .map(JokeCategory::valueOf)
                .collect(Collectors.toList())
        );
    }
}
