package lv.ctco.tpl.bff.graphql.types;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLField;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@GraphQLObject
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Joke {

    @GraphQLField
    public String text;
}
