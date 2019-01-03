package ru.photorex.demorestaurant;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EntryPointHttpApiTests extends AbstractHttpApiTests {

    @Test
    public void withoutAuth() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api"));
        resultActions.andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$._links.actual-restaurants.href", is("http://localhost:8080/api/restaurants?day=" + LocalDate.now().toString())))
                .andExpect(jsonPath("$._links.register-new-user.href", is("http://localhost:8080/api/registration")));

        resultActions.andDo(document("entry-point",
                responseFields(fieldWithPath("_links.actual-restaurants.href").description("Link to availiable restaurants current day"),
                        fieldWithPath("_links.register-new-user.href").description("Link for register new user"))));
    }

    @Test
    public void withAdminAuth() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$._links.restaurants.href", is("http://localhost:8080/api/restaurants/all")))
                .andExpect(jsonPath("$._links.actual-restaurants.href", is("http://localhost:8080/api/restaurants?day=" + LocalDate.now().toString())))
                .andExpect(jsonPath("$._links.dishes.href", is("http://localhost:8080/api/dishes")))
                .andExpect(jsonPath("$._links.register-new-admin.href", is("http://localhost:8080/api/registration/admin")));

        resultActions.andDo(document("entry-point-admin"));
    }

    @Test
    public void withUserAuth() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/api")
                .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
                .with(user(userDetailsService.loadUserByUsername("user"))));

        resultActions.andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/hal+json;charset=UTF-8"))
                .andExpect(jsonPath("$._links.actual-restaurants.href", is("http://localhost:8080/api/restaurants?day=" + LocalDate.now().toString())));

        resultActions.andDo(document("entry-point-user"));
    }
}
