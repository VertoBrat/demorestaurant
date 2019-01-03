package ru.photorex.demorestaurant;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.photorex.demorestaurant.HttpApiTestsUtil.getUserForRegister;

public class UserHttpApiTests extends AbstractHttpApiTests {

    @Test
    public void register() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getUserForRegister(false)));

        resultActions.andExpect(status().isCreated()).andDo(document("register"));
    }

    @Test
    public void registerAdmin() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/registration/admin")
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getUserForRegister(true))
                .with(user(userDetailsService.loadUserByUsername("admin"))));

        resultActions.andExpect(status().isCreated()).andDo(document("register-admin"));
    }


}
