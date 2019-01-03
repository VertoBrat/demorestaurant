package ru.photorex.demorestaurant;

import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteHttpApiTests extends AbstractHttpApiTests {

    @Test
    public void addVote() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/votes/1")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .header("rank", 10)
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isCreated())
                .andDo(document("add-vote"));
    }
}
