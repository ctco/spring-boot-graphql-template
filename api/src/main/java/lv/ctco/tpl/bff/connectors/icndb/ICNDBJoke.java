package lv.ctco.tpl.bff.connectors.icndb;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ICNDBJoke {

    String id;
    String joke;
    List<String> categories;
}
