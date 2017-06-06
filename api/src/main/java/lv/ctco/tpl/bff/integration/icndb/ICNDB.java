package lv.ctco.tpl.bff.integration.icndb;

import feign.RequestLine;
import lv.ctco.tpl.bff.integration.icndb.jokes.JokeResponseModel;

public interface ICNDB {

    @RequestLine("GET /jokes/random")
    JokeResponseModel getRandomJoke();
}
