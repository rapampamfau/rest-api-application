package com.crud.tasks.trello.config;

import com.crud.tasks.config.AdminConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminConfigTest {

    @Test
    void shouldNotShowInformation() {
        //Given
        AdminConfig adminConfig = new AdminConfig();
        //When
        String result = adminConfig.getAdminMail();
        //Then
        assertNull(result);
    }
}
