package np.com.debid.qropsproductservice.service;

import np.com.debid.qropsproductservice.dto.ProductRequest;
import np.com.debid.qropsproductservice.dto.ProductResponse;
import np.com.debid.qropsproductservice.entity.Product;
import np.com.debid.qropsproductservice.repository.ProductRepository;
import np.com.debid.restrocommons.dto.ValidateDTO;
import np.com.debid.restrocommons.exception.CustomException;
import np.com.debid.restrocommons.service.RestaurantClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static np.com.debid.qropsproductservice.constant.Constant.ErrorCodes.PRODUCT_NOT_FOUND_ERROR_CODE;
import static np.com.debid.qropsproductservice.constant.Constant.Messages.PRODUCT_NOT_FOUND;
import static np.com.debid.restrocommons.constant.Constant.ErrorCodes.UNAUTHORIZED_ACCESS_IN_RESTAURANT_DATA_ERROR_CODE;
import static np.com.debid.restrocommons.constant.Constant.Messages.UNAUTHORIZED_ACCESS_IN_RESTAURANT_DATA;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestaurantClient restaurantClient;
    @Autowired
    ModelMapper modelMapper;

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategoryId(productRequest.getCategoryId());
        product.setRestaurantId(productRequest.getRestaurantId());

        return productRepository.save(product);
    }

    public ProductResponse getProductById(Long id) {
        Product product = getProduct(id);
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .restaurantId(product.getRestaurantId())
                .build();
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND, 404, PRODUCT_NOT_FOUND_ERROR_CODE));
    }

    public List<ProductResponse> getAllProductsByRestaurant(UUID tenantId) {
        List<Product> products = productRepository.findByRestaurantId(tenantId);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategoryId(), product.getRestaurantId(), product.getAvailable()))
                .toList();
    }

    public void deleteProduct(Long id) {
        Product product = getProduct(id);
        productRepository.deleteById(product.getId());
    }

    public void validateRestaurantWithUser(Long userId, UUID tenantId) {
        ValidateDTO validateDTO = new ValidateDTO();
        validateDTO.setTenantId(tenantId);
        validateDTO.setUserId(userId);
        if (!this.restaurantClient.validateRestaurant(validateDTO)) {
            // Restaurant ID and User ID mismatch
            throw new CustomException(UNAUTHORIZED_ACCESS_IN_RESTAURANT_DATA, 404, UNAUTHORIZED_ACCESS_IN_RESTAURANT_DATA_ERROR_CODE);
        }
    }
}
