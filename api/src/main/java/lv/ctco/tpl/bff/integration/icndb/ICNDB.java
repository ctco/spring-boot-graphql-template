package lv.ctco.tpl.bff.integration.icndb;

import feign.Param;
import feign.RequestLine;
import lv.ctco.tpl.bff.graphql.types.JokeCategory;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeResponseModel;

public interface ICNDB {

    @RequestLine("GET /jokes/random")
    JokeResponseModel getRandomJoke();

    @RequestLine(value = "GET /jokes/random?limitTo={category}", decodeSlash = false)
    JokeResponseModel getRandomJokeByCategory(
        @Param(value = "category", expander = CategoryExpander.class) JokeCategory category
    );

    @RequestLine(value = "GET /jokes/{id}")
    JokeResponseModel getJokeById(
        @Param(value = "id") String id
    );

    class CategoryExpander implements Param.Expander {
        @Override
        public String expand(Object value) {
            return String.format("[%s]", ((JokeCategory) value).name().toLowerCase());
        }
    }
}
