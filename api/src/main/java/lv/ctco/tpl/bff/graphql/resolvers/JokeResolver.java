package lv.ctco.tpl.bff.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.netflix.hystrix.HystrixCommand;
import lv.ctco.tpl.bff.graphql.types.Joke;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;
import lv.ctco.tpl.bff.graphql.types.JokeCategoryInput;
import lv.ctco.tpl.bff.integration.icndb.ICNDB;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeResponseModel;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeValueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static net.javacrumbs.futureconverter.java8rx.FutureConverter.toCompletableFuture;

@Component
public class JokeResolver implements GraphQLResolver<JokeQuery> {

    @Autowired
    private ICNDB icndb;

    public CompletableFuture<Joke> getJokeByCategory(JokeQuery root, JokeCategoryInput request) {
        JokeCategory category = request.getCategory();
        HystrixCommand<JokeResponseModel> searchCommand = category != null ? icndb.getRandomJokeByCategory(category) : icndb.getRandomJoke();
        return toCompletableFuture(searchCommand
            .toObservable()
            .map(jokeValue -> mapToJoke(jokeValue.getValue()))
            .toSingle());
    }

    public CompletableFuture<Joke> getJokeById(JokeQuery root, String id) {
        return toCompletableFuture(icndb.getJokeById(id)
            .toObservable()
            .map(response -> mapToJoke(response.getValue()))
            .toSingle());
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
