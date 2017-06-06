package lv.ctco.tpl.bff.graphql;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLMutation;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLSchema;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLSchemaQuery;

@GraphQLSchema
public class Schema {

    @GraphQLSchemaQuery
    public Query root;

    @GraphQLMutation
    public String stub() {
        return "stub";
    }
}
