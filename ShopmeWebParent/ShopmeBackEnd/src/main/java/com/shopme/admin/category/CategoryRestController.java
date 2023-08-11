package com.shopme.admin.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories/check_unique")
    public String CheckUnique(Integer id, String name, String alias){
        return categoryService.CheckUnique(id, name, alias);
    }
}
