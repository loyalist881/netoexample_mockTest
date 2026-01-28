package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    @Test
    void sendMessageRUSSIA() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP)).
                thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).
                thenReturn("Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);

        String message = messageSender.send(header);

        Assertions.assertEquals("Добро пожаловать", message);
        Mockito.verify(geoService).byIp(GeoServiceImpl.MOSCOW_IP);
        Mockito.verify(localizationService).locale(Country.RUSSIA);
    }

    @Test
    void sendMessageRUSSIAPrefix() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        String ip = "172.0.0.1";
        Mockito.when(geoService.byIp(ip)).
                thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).
                thenReturn("Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String message = messageSender.send(header);

        Assertions.assertEquals("Добро пожаловать", message);
        Mockito.verify(geoService).byIp(ip);
        Mockito.verify(localizationService).locale(Country.RUSSIA);
    }

    @Test
    void sendMessageUSA() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP)).
                thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).
                thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> header = new HashMap<>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);

        String message = messageSender.send(header);

        Assertions.assertEquals("Welcome", message);
        Mockito.verify(geoService).byIp(GeoServiceImpl.NEW_YORK_IP);
        Mockito.verify(localizationService).locale(Country.USA);
    }

    @Test
    void sendMessageUSAPrefix() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        String ip = "96.12.34.56";
        Mockito.when(geoService.byIp(ip)).
                thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA)).
                thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> header = new HashMap<>();
        header.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String message = messageSender.send(header);

        Assertions.assertEquals("Welcome", message);
        Mockito.verify(geoService).byIp(ip);
        Mockito.verify(localizationService).locale(Country.USA);
    }
}
