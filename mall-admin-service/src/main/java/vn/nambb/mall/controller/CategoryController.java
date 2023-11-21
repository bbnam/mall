package vn.nambb.mall.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.nambb.mall.common.response.CommonResponse;
import vn.nambb.mall.dto.CategoryParam;
import vn.nambb.mall.service.CategoryService;

@AllArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity<CommonResponse<String>> create(
            @Valid @RequestBody CategoryParam categoryParam,
            @RequestPart("file") MultipartFile file) {
        int count = categoryService.create(categoryParam, file);
        if (count == 1) {
            return CommonResponse.success("create success");
        }

        return CommonResponse.fail();
    }

    @PostMapping("update")
    public ResponseEntity<CommonResponse<String>> update(@RequestBody CategoryParam categoryParam) {
        int count = categoryService.update(categoryParam);
        if (count == 1) {
            return CommonResponse.success("update success");
        }
        return CommonResponse.fail();
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable Long id) {
        int count = categoryService.delete(id);
        if (count == 1) {
            return CommonResponse.success("delete success");
        }
        return CommonResponse.fail();
    }

    @PostMapping("get")
    public String get(String name) {
        return name;
    }

}
