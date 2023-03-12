package pl.server;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.server.model.Category;
import pl.server.repository.CategoryRepository;
import pl.server.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void saveCategoryTest() {
        Category category = new Category();
        category.setName("Test Category");

        when(categoryRepository.save(category)).thenReturn(category);

        categoryService.save(category);

        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void findAllCategoriesTest() {
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setName("Category 1");
        categories.add(category1);

        Category category2 = new Category();
        category2.setName("Category 2");
        categories.add(category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.findAll();

        assertEquals(categories.size(), result.size());
        assertTrue(result.containsAll(categories));
    }

    @Test
    public void findCategoryByNameTest() {
        String categoryName = "Test Category";
        Category category = new Category();
        category.setName(categoryName);

        when(categoryRepository.findCategoryByName(categoryName)).thenReturn(category);

        Category result = categoryService.findCategoryByName(categoryName);

        assertEquals(category, result);
    }

    @Test
    public void findCategoryByIdTest() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);

        when(categoryRepository.findCategoryById(categoryId)).thenReturn(category);

        Category result = categoryService.findCategoryById(categoryId);

        assertEquals(category, result);
    }

    @Test
    public void getAllCategoriesTest() {
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setName("Category 1");
        categories.add(category1);

        Category category2 = new Category();
        category2.setName("Category 2");
        categories.add(category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(categories.size(), result.size());
        assertTrue(result.containsAll(categories));
    }

    @Test
    public void existsByNameTest() {
        String categoryName = "Test Category";

        when(categoryRepository.existsByName(categoryName)).thenReturn(true);

        Boolean result = categoryService.existsByName(categoryName);

        assertTrue(result);
    }

    @Test
    public void removeCategoryTest() {
        Category category = new Category();
        category.setName("Test Category");

        categoryService.removeCategory(category);

        verify(categoryRepository, times(1)).delete(category);
    }
}
