package vn.nambb.mall.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.nambb.mall.common.response.CommonResponse;
import vn.nambb.mall.dto.ProductParam;
import vn.nambb.mall.service.ProductService;

@AllArgsConstructor
@RestController()
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("create")
    public ResponseEntity<CommonResponse<String>> create(@Valid @RequestBody ProductParam productParam) {
        int count = productService.create(productParam);
        if (count == 1) {
            return CommonResponse.success("create success");
        }
        return CommonResponse.fail();
    }

    @PutMapping("update")
    public ResponseEntity<CommonResponse<String>> update(@Valid @RequestBody ProductParam productParam) {
        int count = productService.update(productParam);
        if (count == 1) {
            return CommonResponse.success("update success");
        }
        return CommonResponse.fail();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<CommonResponse<String>> delete(@PathVariable Long id) {
        int count = productService.delete(id);
        if (count == 1) {
            return CommonResponse.success("delete success");
        }
        return CommonResponse.fail();
    }

    @GetMapping("get")
    public String get(String name) {
        return name;
    }
}
