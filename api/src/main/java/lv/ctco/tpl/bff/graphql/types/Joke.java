package lv.ctco.tpl.bff.graphql.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Joke {

    private String text;
    private String id;
    private List<JokeCategory> categories;
}
