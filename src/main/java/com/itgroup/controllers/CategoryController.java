    package com.itgroup.controllers;

    import com.itgroup.dto.CategoryDto;
    import com.itgroup.service.CategoryService;
    import lombok.AllArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @AllArgsConstructor
    @RestController
    @RequestMapping("api/categories")
    public class CategoryController {

        private CategoryService categoryService;

//        @PostMapping("")
//        public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category) {
//            CategoryDto savedCategory = categoryService.createCategory(category);
//            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
//        }

        @GetMapping("")
        public ResponseEntity<List<CategoryDto>> showAll() {
            List<CategoryDto> categories = categoryService.getAllCategories();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

//        @GetMapping("/{id}")
//        public ResponseEntity<CategoryDto> showById(@PathVariable("id") Long id) {
//            CategoryDto category = categoryService.getCategoryById(id);
//            return new ResponseEntity<>(category, HttpStatus.OK);
//        }
//
//        @PutMapping("/{id}")
//        public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id,
//                                                          @RequestBody CategoryDto category) {
//            category.setId(id);
//            CategoryDto updatedCategory = categoryService.updateCategory(category);
//            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
//            categoryService.delete(id);
//            return new ResponseEntity<>("Category successfully deleted", HttpStatus.OK);
//        }
    }
