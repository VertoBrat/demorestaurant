package ru.photorex.demorestaurant;

import org.junit.Test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static ru.photorex.demorestaurant.HttpApiTestsUtil.*;

public class RestaurantHttpApiTests extends AbstractHttpApiTests {

    @Test
    public void withoutAuth() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/restaurants"));

        resultActions.andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/hal+json;charset=UTF-8"))
                .andExpect(content().json(getResponseRestaurantsWithoutAuth()));
    }

    @Test
    public void withAuth() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/restaurants")
                .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
                .with(user(userDetailsService.loadUserByUsername("user"))));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.restaurants[0]._links.add-vote.href", is("http://localhost:8080/api/votes/1{?rank}")));

        resultActions.andDo(document("actual-restaurants-with-auth"));
    }

    @Test
    public void withAdminAuth() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/restaurants")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.restaurants[0].dishes[0]._links.self.href", is("http://localhost:8080/api/dishes/1")));
    }

    @Test
    public void getAllForAdmin() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/restaurants/all")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(getForAdmin()))
                .andDo(document("all-restaurants-for-admin"));
    }

    @Test
    public void getFirstForAdmin() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/restaurants/1")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isOk())
                .andExpect(content().json(getOneRestaurantForAdmin()))
                .andDo(document("get-one-restaurant"));
    }

    @Test
    public void add() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/restaurants")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getNewRestaurant())
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isOk())
                .andDo(document("create-restaurant"));
    }

    @Test
    public void update() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/restaurants/1")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getUpdatedRestaurant())
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isOk())
                .andDo(document("update-restaurant"));
    }

    @Test
    public void delete() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/restaurants/1")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isNoContent())
                .andDo(document("delete-restaurant"));
    }
}
