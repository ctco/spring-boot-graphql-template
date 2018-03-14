package lv.ctco.tpl.bff.connectors.icndb;

import feign.Param;
import feign.RequestLine;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;

public interface ICNDB {

    @RequestLine("GET /jokes/random")
    ICNDBJokeEnvelop getRandomJoke();

    @RequestLine(value = "GET /jokes/random?limitTo={category}", decodeSlash = false)
    ICNDBJokeEnvelop getRandomJokeLimitedToCategory(
        @Param(value = "category", expander = CategoryExpander.class) JokeCategory category
    );

    @RequestLine(value = "GET /jokes/{id}")
    ICNDBJokeEnvelop getJokeById(
        @Param(value = "id") String id
    );

    class CategoryExpander implements Param.Expander {
        @Override
        public String expand(Object value) {
            return String.format("[%s]", ((JokeCategory) value).name().toLowerCase());
        }
    }
}
