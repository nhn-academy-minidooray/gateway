package com.nhnacademy.minidooray.gateway.controller.account;

import com.nhnacademy.minidooray.gateway.service.account.AccountClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class AccountDeleteControllerTest {

  @Mock
  private AccountClientService accountClientService;

  @InjectMocks
  private AccountDeleteController accountDeleteController;

  private MockMvc mockMvc;

  @Test
  void testDoDeleteSuccess() throws Exception {
    mockMvc = standaloneSetup(accountDeleteController).build();

    String accountId = "testAccountId";

    when(accountClientService.deleteAccount(any())).thenReturn(true);

    mockMvc.perform(MockMvcRequestBuilders.delete("/delete")
            .sessionAttr("USER_INFO", accountId))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

    verify(accountClientService, times(1)).deleteAccount(any());
  }

  @Test
  void testDoDeleteFailure() throws Exception {
    mockMvc = standaloneSetup(accountDeleteController).build();

    String accountId = "testAccountId";

    when(accountClientService.deleteAccount(any())).thenReturn(false);

    mockMvc.perform(MockMvcRequestBuilders.delete("/delete")
            .sessionAttr("USER_INFO", accountId))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

    verify(accountClientService, times(1)).deleteAccount(any());
  }
}
