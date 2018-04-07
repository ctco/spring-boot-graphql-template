package lv.ctco.tpl.bff.graphql;

import lombok.SneakyThrows;
import lombok.val;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GraphQLIntegrationTest {

    private static final String GRAPHQL_PATH = "/graphql";

    private static final String QUERY = "{jokes {jokeById(id: \"77\"){id,text}}}";

    private static final String JOKE_ID = "77";

    private static final String JOKE_TEXT = "Chuck Norris can divide by zero.";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void testGetJokeById() {
        val result = graphQLPost();

        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.errors").doesNotExist())
            .andExpect(jsonPath("$.data.jokes.jokeById.id").value(JOKE_ID))
            .andExpect(jsonPath("$.data.jokes.jokeById.text").value(JOKE_TEXT));
    }

    @SneakyThrows
    private ResultActions graphQLPost() {
        return mockMvc.perform(post(GRAPHQL_PATH).content(request()));
    }

    @SneakyThrows
    private String request() {
        val jsonQuery = new JSONObject();
        jsonQuery.put("query", QUERY);
        return jsonQuery.toString();
    }
}
