package com.nhnacademy.minidooray.gateway.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
class WelcomePageControllerTest {

  @Test
  void testGetWelcomePage() {
    // Given
    WelcomePageController welcomePageController = new WelcomePageController();
    Model model = mock(Model.class);

    // When
    String result = welcomePageController.getWelcomePage();

    // Then
    assertEquals("welcome", result);
  }
}
