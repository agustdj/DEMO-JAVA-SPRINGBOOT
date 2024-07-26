package BUOI2.LAB02.controller;

import BUOI2.LAB02.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showAddForm(Model model) {
        return "/products/product-list";
    }
}