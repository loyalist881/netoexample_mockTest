package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;


import static org.mockito.Mockito.spy;

class LocalizationServiceTest {
    private LocalizationService service;

    @BeforeEach
    void setUp() {
        service = spy(new LocalizationServiceImpl());
    }

    @Test
    void returnsRussia() {
        Country country = Country.RUSSIA;

        String result = service.locale(country);

        Assertions.assertEquals("Добро пожаловать", result);
        Mockito.verify(service).locale(country);
    }

    @ParameterizedTest(name = "Country {0} returns Welcome")
    @EnumSource(value = Country.class,
            names = "RUSSIA",
            mode = EnumSource.Mode.EXCLUDE)
    void returnsWelcomeForAllNonRussia (Country country) {
        String result = service.locale(country);

        Assertions.assertEquals("Welcome", result);

        Mockito.verify(service).locale(country);
    }
}
