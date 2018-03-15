package lv.ctco.tpl.bff.graphql.models;

import com.netflix.hystrix.HystrixCommand;
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
        HystrixCommand<ICNDBJokeEnvelope> icndbJokeEnvelopeCommand = icndb.getRandomJokeLimitedToCategory(category);
        ICNDBJoke icndbJoke = icndbJokeEnvelopeCommand.execute().getValue();
        return mapToJoke(icndbJoke);
    }

    public Joke getJokeById(String id) {
        HystrixCommand<ICNDBJokeEnvelope> icndbJokeEnvelopeCommand = icndb.getJokeById(id);
        ICNDBJoke icndbJoke = icndbJokeEnvelopeCommand.execute().getValue();
        return mapToJoke(icndbJoke);
    }

    public Joke getRandomJoke() {
        HystrixCommand<ICNDBJokeEnvelope> icndbJokeEnvelopeCommand = icndb.getRandomJoke();
        ICNDBJoke icndbJoke = icndbJokeEnvelopeCommand.execute().getValue();
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
