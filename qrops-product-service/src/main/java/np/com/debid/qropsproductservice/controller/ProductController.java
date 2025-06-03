package np.com.debid.qropsproductservice.controller;

import jakarta.validation.Valid;
import np.com.debid.qropsproductservice.dto.ProductRequest;
import np.com.debid.qropsproductservice.dto.ProductResponse;
import np.com.debid.qropsproductservice.entity.Product;
import np.com.debid.qropsproductservice.service.ProductService;
import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static np.com.debid.qropsproductservice.constant.Constant.Messages.PRODUCTS_FETCHED;
import static np.com.debid.qropsproductservice.constant.Constant.Messages.PRODUCT_CREATED;
import static np.com.debid.qropsproductservice.constant.Constant.Messages.PRODUCT_DELETED;
import static np.com.debid.qropsproductservice.constant.Constant.Messages.PRODUCT_FETCHED;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper<Product>> createCategory(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseUtil.successResponse(PRODUCT_CREATED, productService.createProduct(productRequest));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseWrapper<ProductResponse>> getCategoryById(@PathVariable Long id) {
        return ResponseUtil.successResponse(PRODUCT_FETCHED, productService.getProductById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteCategory(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseUtil.successResponse(PRODUCT_DELETED, null);
    }

    @GetMapping("/get/restaurant")
    public ResponseEntity<ResponseWrapper<List<ProductResponse>>> getAllCategoriesByRestaurant() {
        return ResponseUtil.successResponse(PRODUCTS_FETCHED, productService.getAllProductsByRestaurant());
    }
}
