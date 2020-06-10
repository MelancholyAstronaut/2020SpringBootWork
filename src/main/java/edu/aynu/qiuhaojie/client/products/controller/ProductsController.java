package edu.aynu.qiuhaojie.client.products.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.aynu.qiuhaojie.client.products.service.ProductsService;
import edu.aynu.qiuhaojie.commons.beans.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client/product")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    /**
     * @param Category 类别
     * @return 查询出来的List<product></>
     */
    //StringBuffer
    @RequestMapping("/findProductByCategory")
    public String findProductByCategory(String Category,
                                        @RequestParam(defaultValue = "1") int pageIndex,
                                        Model model) {

        PageHelper.startPage(pageIndex, 3);
        List<Products> list = productsService.findProductByCategory(Category);

        PageInfo<Products> page = new PageInfo<>(list);
        model.addAttribute("name", Category);
        model.addAttribute("list", list);
        model.addAttribute("page", page);

        return "/client/product_list";
    }

    @RequestMapping("productSearchByName")
    public String productSearchByName(String bookName,
                                      @RequestParam(defaultValue = "1") int pageIndex,
                                      Model model) {
        PageHelper.startPage(pageIndex, 3);
        List<Products> list = productsService.findProductsByName(bookName);
        PageInfo<Products> page = new PageInfo<>(list);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("name", bookName);
        return "/client/product_search_list";
    }

    @RequestMapping("/findProductById")
    public String findProductById(Integer id,
                                  Model model) {
        Products products = productsService.findProductById(id);
        model.addAttribute("product", products);
        return "/client/info";

    }
}
