package np.com.debid.restrocategoryservice.service;

import np.com.debid.restrocategoryservice.dto.CategoryRequest;
import np.com.debid.restrocategoryservice.dto.CategoryResponse;
import np.com.debid.restrocategoryservice.entity.Category;
import np.com.debid.restrocategoryservice.repository.CategoryRepository;
import np.com.debid.restrocommons.exception.CustomException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static np.com.debid.restrocategoryservice.constant.Constant.ErrorCodes.CATEGORY_NOT_FOUND_ERROR_CODE;
import static np.com.debid.restrocategoryservice.constant.Constant.Messages.CATEGORY_NOT_FOUND;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;

    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        category.setRestaurantId(categoryRequest.getRestaurantId());

        return categoryRepository.save(category);
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = getCategory(id);
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .restaurantId(category.getRestaurantId())
                .build();
    }

    private Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CustomException(CATEGORY_NOT_FOUND, 404, CATEGORY_NOT_FOUND_ERROR_CODE));
    }

    public List<CategoryResponse> getAllCategoriesByRestaurant() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .map(category -> new CategoryResponse(category.getId(), category.getName(), category.getDescription(), category.getRestaurantId()))
                .toList();
    }

    public void deleteCategory(Long id) {
        Category category = getCategory(id);
        categoryRepository.deleteById(category.getId());
    }
}
