package lv.ctco.tpl.bff.graphql.models;

import lv.ctco.tpl.bff.connectors.icndb.ICNDB;
import lv.ctco.tpl.bff.connectors.icndb.ICNDBJoke;
import lv.ctco.tpl.bff.connectors.icndb.ICNDBJokeEnvelope;
import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;
import lv.ctco.tpl.bff.graphql.types.JokeCategoryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JokeModel {

    @Autowired
    private ICNDB icndb;

    public Joke getRandomJokeLimitedToCategory(JokeCategoryInput request) {
        JokeCategory category = request.getCategory();
        ICNDBJokeEnvelope icndbJokeEnvelope = icndb.getRandomJokeLimitedToCategory(category);
        ICNDBJoke icndbJoke = icndbJokeEnvelope.getValue();
        return mapToJoke(icndbJoke);
    }

    public Joke getJokeById(String id) {
        ICNDBJokeEnvelope icndbJokeEnvelope = icndb.getJokeById(id);
        ICNDBJoke icndbJoke = icndbJokeEnvelope.getValue();
        return mapToJoke(icndbJoke);
    }

    public Joke getRandomJoke() {
        ICNDBJokeEnvelope icndbJokeEnvelop = icndb.getRandomJoke();
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
