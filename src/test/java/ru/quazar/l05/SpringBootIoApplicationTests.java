package ru.quazar.spring_boot_io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootIoApplicationTests {

    @BeforeEach
    public void setUp() {

    }

    @Test
    @DisplayName("Проверка загрузки файла ...")
    public void contextLoads() {
    }

}
