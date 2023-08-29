package com.shopme.common.setting;

import com.shopme.setting.SettingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SettingRepositoryTest {

    @Autowired
    SettingRepository repo;

    @Test
    public void testfindByTwoCategories(){
        List<Setting> setting = repo.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
        setting.forEach(System.out::println);
    }
}
