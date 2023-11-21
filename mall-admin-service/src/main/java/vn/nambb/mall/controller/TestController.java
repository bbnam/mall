package vn.nambb.mall.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.nambb.mall.entity.CategoryEntity;
import vn.nambb.mall.repository.CategoryRepository;

@AllArgsConstructor
@RestController
public class TestController {
    private final CategoryRepository categoryRepository;


    @GetMapping("/test")
    public ResponseEntity<String> createCardInfoByOcr() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryRepository.save(categoryEntity);

        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
