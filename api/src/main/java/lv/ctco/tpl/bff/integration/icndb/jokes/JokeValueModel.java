package lv.ctco.tpl.bff.integration.icndb.jokes;

import lombok.Getter;

import java.util.List;

@Getter
public class JokeValueModel {
    private String id;
    private String joke;
    private List<String> categories;
}
