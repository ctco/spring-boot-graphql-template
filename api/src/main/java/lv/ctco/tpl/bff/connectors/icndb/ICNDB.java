package lv.ctco.tpl.bff.connectors.icndb;

import feign.Param;
import feign.RequestLine;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;

public interface ICNDB {

    @RequestLine("GET /jokes/random")
    ICNDBJokeEnvelope getRandomJoke();

    @RequestLine(value = "GET /jokes/random?limitTo={category}", decodeSlash = false)
    ICNDBJokeEnvelope getRandomJokeLimitedToCategory(
        @Param(value = "category", expander = CategoryExpander.class) JokeCategory category
    );

    @RequestLine(value = "GET /jokes/{id}")
    ICNDBJokeEnvelope getJokeById(
        @Param(value = "id") String id
    );

    class CategoryExpander implements Param.Expander {
        @Override
        public String expand(Object value) {
            return String.format("[%s]", ((JokeCategory) value).name().toLowerCase());
        }
    }
}
