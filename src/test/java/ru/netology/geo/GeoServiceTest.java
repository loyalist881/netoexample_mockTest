package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.mockito.Mockito.spy;

class GeoServiceTest {
    private GeoService geoService;

    @BeforeEach
    void setUp() {
        geoService = spy(new GeoServiceImpl());
    }

    @Test
    void byIp_localhost() {
        String ip = GeoServiceImpl.LOCALHOST;

        Location location = geoService.byIp(ip);

        Assertions.assertNotNull(location);
        Assertions.assertNull(location.getCity());
        Assertions.assertNull(location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());

        Mockito.verify(geoService).byIp(ip);
    }

    @Test
    void byIp_MOSCOWIp() {
        String ip = GeoServiceImpl.MOSCOW_IP;

        Location location = geoService.byIp(ip);

        Assertions.assertNotNull(location);
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertEquals("Lenina", location.getStreet());
        Assertions.assertEquals(15, location.getBuiling());

        Mockito.verify(geoService).byIp(ip);
    }

    @Test
    void byIp_MOSCOWPrefix() {
        String ip = "172.0.0.1";

        Location location = geoService.byIp(ip);

        Assertions.assertNotNull(location);
        Assertions.assertEquals("Moscow", location.getCity());
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());

        Mockito.verify(geoService).byIp(ip);
    }

    @Test
    void byIp_USAIp() {
        String ip = GeoServiceImpl.NEW_YORK_IP;

        Location location = geoService.byIp(ip);

        Assertions.assertNotNull(location);
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertEquals(" 10th Avenue", location.getStreet());
        Assertions.assertEquals(32, location.getBuiling());

        Mockito.verify(geoService).byIp(ip);
    }

    @Test
    void byIp_USAPrefix() {
        String ip = "96.12.34.56";

        Location location = geoService.byIp(ip);

        Assertions.assertNotNull(location);
        Assertions.assertEquals("New York", location.getCity());
        Assertions.assertEquals(Country.USA, location.getCountry());
        Assertions.assertNull(location.getStreet());
        Assertions.assertEquals(0, location.getBuiling());

        Mockito.verify(geoService).byIp(ip);
    }
}
