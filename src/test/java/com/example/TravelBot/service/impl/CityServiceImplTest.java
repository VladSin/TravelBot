package com.example.TravelBot.service.impl;

import com.example.TravelBot.entity.CityEntity;
import com.example.TravelBot.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class CityServiceImplTest {

    @Autowired
    private CityService cityService;

    @Test
    void save() {
        final CityEntity cityToSave = new CityEntity(null, "Minsk", "You can visit the National Library");
        final CityEntity savedCity = cityService.save(cityToSave);

        assertNotNull(savedCity);
        assertEquals(cityToSave.getName(), savedCity.getName());
        assertEquals(cityToSave.getInfo(), savedCity.getInfo());
    }

    @Test
    void findById() {
        final CityEntity cityToSave = new CityEntity(null, "Brest", "You can visit the Brest Fortress");
        final CityEntity savedCity = cityService.save(cityToSave);
        final CityEntity city = cityService.findById(savedCity.getId());
        assertNotNull(city);
    }

    @Test
    void findByName() {
        final CityEntity cityToSave = new CityEntity(null, "Moscow", "You can visit the Kremlin Palace");
        final CityEntity savedCity = cityService.save(cityToSave);
        final CityEntity city = cityService.findByName(savedCity.getName());
        assertNotNull(city);
    }

    @Test
    void getAll() {
        final CityEntity cityToSave1 = new CityEntity(null, "Minsk1", "You can visit the National Library");
        final CityEntity cityToSave2 = new CityEntity(null, "Minsk2", "You can visit the National Library");
        cityService.save(cityToSave1);
        cityService.save(cityToSave2);
        List<CityEntity> cities = cityService.getAll();
        assertNotNull(cities);
    }

    @Test
    void updateName() {
        final CityEntity cityToSave = new CityEntity(null, "SPB", "You can visit the Saint Isaac's Cathedral");
        final CityEntity savedCity = cityService.save(cityToSave);

        cityService.updateName(savedCity.getId(), "Petersburg");

        final CityEntity afterUpdate = cityService.findById(savedCity.getId());

        assertNotNull(afterUpdate);
        assertEquals(savedCity.getId(), afterUpdate.getId());
        assertEquals(savedCity.getInfo(), afterUpdate.getInfo());
        assertEquals("Petersburg", afterUpdate.getName());
    }

    @Test
    void updateInfo() {
        final CityEntity cityToSave = new CityEntity(null, "SPB", "You can visit the Saint Isaac's Cathedral");
        final CityEntity savedCity = cityService.save(cityToSave);

        cityService.updateInfo(savedCity.getId(), "You can visit Nevsky Prospect");

        final CityEntity afterUpdate = cityService.findById(savedCity.getId());

        assertNotNull(afterUpdate);
        assertEquals(savedCity.getId(), afterUpdate.getId());
        assertEquals(savedCity.getName(), afterUpdate.getName());
        assertEquals("You can visit Nevsky Prospect", afterUpdate.getInfo());
    }

    @Test
    void delete() {
        final CityEntity cityToSave = new CityEntity(null, "Minsk3", "You can visit the National Library");
        final CityEntity savedCity = cityService.save(cityToSave);
        cityService.delete(savedCity.getId());
        final CityEntity deletedCity = cityService.findById(savedCity.getId());
        assertNull(deletedCity);
    }
}