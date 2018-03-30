package lv.ctco.tpl.bff.graphql;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.SneakyThrows;
import lombok.val;
import lv.ctco.tpl.bff.configuration.AppProperties;
import lv.ctco.tpl.bff.connectors.icndb.ICNDB;
import lv.ctco.tpl.bff.connectors.icndb.ICNDBJoke;
import lv.ctco.tpl.bff.connectors.icndb.ICNDBJokeEnvelope;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class GraphQLQueryTest {

    private static final String GRAPHQL_PATH = "/graphql";

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ICNDB icndb;

    @Test
    @SneakyThrows
    public void testGetJokeById() {

        val joke =
            "{" +
                "  jokes {" +
                "    jokeById(id: \"77\") {" +
                "      id," +
                "      text" +
                "    }" +
                "  }" +
                "}";

        doReturn(mockResponse())
            .when(icndb).getJokeById("77");

        val result = graphQLPost(joke);

        result.andExpect(status().isOk())
            .andExpect(jsonPath("$.errors").doesNotExist())
            .andExpect(jsonPath("$.data.jokes.jokeById.id").value("77"))
            .andExpect(jsonPath("$.data.jokes.jokeById.text").value("Chuck Norris can divide by zero."));
    }

    @SneakyThrows
    private ResultActions graphQLPost(String query) {
        return mockMvc.perform(post(GRAPHQL_PATH).content(request(query)));
    }

    @SneakyThrows
    private String request(String query) {
        val jsonQuery = new JSONObject();
        jsonQuery.put("query", query);
        return jsonQuery.toString();
    }

    private HystrixCommand<ICNDBJokeEnvelope> mockResponse() {
        return new HystrixCommand<ICNDBJokeEnvelope>(HystrixCommandGroupKey.Factory.asKey(appProperties.getIcndbUrl())) {
            @Override
            protected ICNDBJokeEnvelope run() throws Exception {
                ICNDBJokeEnvelope icndbJokeEnvelope = new ICNDBJokeEnvelope();
                icndbJokeEnvelope.setType("success");
                ICNDBJoke value = new ICNDBJoke();
                value.setId("77");
                value.setJoke("Chuck Norris can divide by zero.");
                icndbJokeEnvelope.setValue(value);
                return icndbJokeEnvelope;
            }
        };
    }
}
