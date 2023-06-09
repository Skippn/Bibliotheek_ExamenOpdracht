package com.springBoot.Bibliotheek;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

	// Inject MockMvc
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void loginGet() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("loginPage"))
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"))
				.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("msg"));
	}

	@Test
	void testWrongPassword() throws Exception {
		mockMvc.perform(formLogin("/login").user("username", "Lander").password("password", "TestPass3"))
				.andExpect(status().isFound()).andExpect(redirectedUrl("/login?error"));
	}

	@Test
	void testCorrectPassword() throws Exception {
		mockMvc.perform(formLogin("/login").user("username", "Lander").password("password", "TestPass1"))
				.andExpect(status().isFound()).andExpect(redirectedUrl("/bibliotheek/list"));
	}
	
	@Test
    public void testLoginWithError() throws Exception {
        mockMvc.perform(get("/login?error=true"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"))
                .andExpect(MockMvcResultMatchers.model().attribute("error", "Foute naam of paswoord!"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("msg"));
    }

    @Test
    public void testLoginWithLogout() throws Exception {
        mockMvc.perform(get("/login?logout=true"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"))
                .andExpect(MockMvcResultMatchers.model().attribute("msg", "Je bent succesvol uitgelogd."));
    }
}
