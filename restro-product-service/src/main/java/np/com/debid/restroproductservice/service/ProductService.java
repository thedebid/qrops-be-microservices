package np.com.debid.restroproductservice.service;

import np.com.debid.restrocommons.exception.CustomException;
import np.com.debid.restroproductservice.dto.ProductRequest;
import np.com.debid.restroproductservice.dto.ProductResponse;
import np.com.debid.restroproductservice.entity.Product;
import np.com.debid.restroproductservice.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static np.com.debid.restroproductservice.constant.Constant.ErrorCodes.PRODUCT_NOT_FOUND_ERROR_CODE;
import static np.com.debid.restroproductservice.constant.Constant.Messages.PRODUCT_NOT_FOUND;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;

    public Product createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setRestaurantId(productRequest.getRestaurantId());

        return productRepository.save(product);
    }

    public ProductResponse getProductById(Long id) {
        Product product = getProduct(id);
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .restaurantId(product.getRestaurantId())
                .build();
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND, 404, PRODUCT_NOT_FOUND_ERROR_CODE));
    }

    public List<ProductResponse> getAllProductsByRestaurant() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategoryId(), product.getRestaurantId(), product.getAvailable()))
                .toList();
    }

    public void deleteProduct(Long id) {
        Product product = getProduct(id);
        productRepository.deleteById(product.getId());
    }
}
