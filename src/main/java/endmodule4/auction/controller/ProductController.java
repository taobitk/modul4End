package endmodule4.auction.controller;

import endmodule4.auction.model.Category;
import endmodule4.auction.model.Product;
import endmodule4.auction.service.ICategoryService;
import endmodule4.auction.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;
    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("")
    public ModelAndView listProducts(
            @RequestParam(defaultValue = "") String searchName,
            @RequestParam(required = false) Double searchPrice,
            @RequestParam(defaultValue = "") String searchCategory,
            // Sửa lại dòng này
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Product> products = productService.search(searchName, searchPrice, searchCategory, pageable);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("searchName", searchName);
        modelAndView.addObject("searchPrice", searchPrice);
        modelAndView.addObject("searchCategory", searchCategory);
        return modelAndView;
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }
    @PostMapping("/save")
    public String saveProduct(@Validated @ModelAttribute Product product,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "create";
        }

        productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Thêm mới sản phẩm thành công!");
        return "redirect:/product";
    }

    @PostMapping("/delete")
    public String deleteProducts(@RequestParam("idsToDelete") List<Integer> idsToDelete,
                                 RedirectAttributes redirectAttributes) {
        for (Integer id : idsToDelete) {
            productService.remove(id);
        }
        redirectAttributes.addFlashAttribute("message", "Đã xóa các sản phẩm được chọn!");
        return "redirect:/product";
    }
}