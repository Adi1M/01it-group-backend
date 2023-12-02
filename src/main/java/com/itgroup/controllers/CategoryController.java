    package com.itgroup.controllers;

    import com.itgroup.dto.CategoryRequestDto;
    import com.itgroup.dto.CategoryDto;
    import com.itgroup.service.CategoryService;
    import lombok.AllArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;


    @RestController
    @RequestMapping("api/categories")
    @AllArgsConstructor
    public class CategoryController {

        private CategoryService categoryService;

        @PostMapping("")
        public ResponseEntity<String> createCategory(@RequestBody CategoryRequestDto category) {
            categoryService.createCategory(category);
            return new ResponseEntity<>("Category successfully created", HttpStatus.CREATED);
        }

        @GetMapping("")
        public ResponseEntity<List<CategoryDto>> showAll() {
            return ResponseEntity.ok(categoryService.getAllCategories());
        }

        @GetMapping("/{id}")
        public ResponseEntity<CategoryDto> showById(@PathVariable("id") Long id) {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> updateCategory(@PathVariable("id") Long id,
                                                          @RequestBody CategoryDto category) {
            categoryService.updateCategory(id, category);
            return ResponseEntity.ok("Category successfully updated");
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category successfully deleted");
        }
    }
