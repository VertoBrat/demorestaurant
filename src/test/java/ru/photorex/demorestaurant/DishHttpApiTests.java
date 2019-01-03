package ru.photorex.demorestaurant;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.photorex.demorestaurant.HttpApiTestsUtil.getNewDish;

public class DishHttpApiTests extends AbstractHttpApiTests {

    @Test
    public void getAll() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/dishes")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements", is(27)));
    }

    @Test
    public void add() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/dishes/1")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getNewDish())
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isCreated())
                .andDo(document("add-dish"));
    }

    @Test
    public void update() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/dishes/1").header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getNewDish())
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isOk())
                .andDo(document("update-dish"));
    }

    @Test
    public void delete() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/dishes/1")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isNoContent())
                .andDo(document("delete-dish"));
    }
}
