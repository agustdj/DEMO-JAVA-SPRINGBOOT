package BUOI2.LAB02.controller;

import BUOI2.LAB02.model.Product;
import BUOI2.LAB02.service.CategoryService;
import BUOI2.LAB02.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService; // Đảm bảo bạn đã inject CategoryService
    // Display a list of all products
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/product-list";
    }
    @GetMapping("/management")
    public String showProductManagement(Model model) {
        return "/products/product-management";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories()); //Load categories
        return "/products/add-product";
    }
    // Process the form for adding a new product
    //@PostMapping("/add")
    /*public String addProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "/products/add-product";
        }
        productService.updateImage(newProduct, imageProduct);
        productService.addProduct(product);
        return "redirect:/products";
    }*/
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories()); //Load categories
        return "/products/update-product";
    }
    // Process the form for updating a product
   /* @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid Product product,
                                BindingResult result) {
        if (result.hasErrors()) {
            product.setId(id); // set id to keep it in the form in case of errors
            return "/products/update-product";
        }
        productService.updateImage(editProduct, imageProduct);
        productService.updateProduct(product);
        return "redirect:/products";
    }*/
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id,@Valid Product editProduct,
                       BindingResult result,
                       @RequestParam MultipartFile imageProduct,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", editProduct);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/products/update-product";
        }
        productService.updateImage(editProduct, imageProduct);
        productService.updateProduct(editProduct);
        return "redirect:/products";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
    @GetMapping("/detail/{id}")
    public String detailProduct(@PathVariable Long id) {
        return "products/product-detail";
    }



    @PostMapping("/add")
    public String  addProduct(@Valid Product newProduct,
                         BindingResult result,
                         @RequestParam MultipartFile imageProduct,
                         Model model)  {
        if (imageProduct.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "uploadStatus";}

        if (result.hasErrors()) {
            model.addAttribute("product", newProduct);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/products/add-product";
        }
        productService.updateImage(newProduct, imageProduct);
        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String searchProductByName(@RequestParam("keyword") String keyword, Model model) {
        List<Product> searchResults = productService.searchProductByName(keyword);
        model.addAttribute("products", searchResults);
        return "products/product-list";
    }
}