package lv.ctco.tpl.bff.connectors.icndb;

import lombok.Getter;
import java.util.List;

@Getter
public class ICNDBJoke {

    String id;
    String joke;
    List<String> categories;
}
