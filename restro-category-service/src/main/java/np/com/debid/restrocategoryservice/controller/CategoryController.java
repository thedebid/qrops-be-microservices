package np.com.debid.restrocategoryservice.controller;

import jakarta.validation.Valid;
import np.com.debid.restrocategoryservice.dto.CategoryRequest;
import np.com.debid.restrocategoryservice.dto.CategoryResponse;
import np.com.debid.restrocategoryservice.entity.Category;
import np.com.debid.restrocategoryservice.service.CategoryService;
import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static np.com.debid.restrocategoryservice.constant.Constant.Messages.CATEGORIES_FETCHED;
import static np.com.debid.restrocategoryservice.constant.Constant.Messages.CATEGORY_CREATED;
import static np.com.debid.restrocategoryservice.constant.Constant.Messages.CATEGORY_DELETED;
import static np.com.debid.restrocategoryservice.constant.Constant.Messages.CATEGORY_FETCHED;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper<Category>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest, @RequestHeader("userId") Long userId, @RequestHeader("X-TenantID") String tenantId) {
        UUID tenantUUID = UUID.fromString(tenantId);
        this.categoryService.validateRestaurantWithUser(userId, tenantUUID);
        return ResponseUtil.successResponse(CATEGORY_CREATED, categoryService.createCategory(categoryRequest, tenantUUID));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseWrapper<CategoryResponse>> getCategoryById(@PathVariable Long id) {
        return ResponseUtil.successResponse(CATEGORY_FETCHED, categoryService.getCategoryById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseUtil.successResponse(CATEGORY_DELETED, null);
    }

    @GetMapping("/get/restaurant")
    public ResponseEntity<ResponseWrapper<List<CategoryResponse>>> getAllCategoriesByRestaurant(@RequestHeader("userId") Long userId, @RequestHeader("X-TenantID") String tenantId) {
        UUID tenantUUID = UUID.fromString(tenantId);
        this.categoryService.validateRestaurantWithUser(userId, tenantUUID);
        return ResponseUtil.successResponse(CATEGORIES_FETCHED, categoryService.getAllCategoriesByRestaurant(tenantUUID));
    }
}
