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

    private static final String QUERY = "{jokes {jokeById(id: \"77\"){id,text}}}";

    private static final String JOKE_ID = "77";

    private static final String JOKE_TEXT = "Chuck Norris can divide by zero.";

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ICNDB icndb;

    @Test
    @SneakyThrows
    public void testGetJokeById() {
        doReturn(mockResponse()).when(icndb).getJokeById(JOKE_ID);

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

    private HystrixCommand<ICNDBJokeEnvelope> mockResponse() {
        return new HystrixCommand<ICNDBJokeEnvelope>(HystrixCommandGroupKey.Factory.asKey(appProperties.getIcndbUrl())) {
            @Override
            protected ICNDBJokeEnvelope run() throws Exception {
                ICNDBJokeEnvelope icndbJokeEnvelope = new ICNDBJokeEnvelope();
                icndbJokeEnvelope.setType("success");
                ICNDBJoke value = new ICNDBJoke();
                value.setId(JOKE_ID);
                value.setJoke(JOKE_TEXT);
                icndbJokeEnvelope.setValue(value);
                return icndbJokeEnvelope;
            }
        };
    }
}
