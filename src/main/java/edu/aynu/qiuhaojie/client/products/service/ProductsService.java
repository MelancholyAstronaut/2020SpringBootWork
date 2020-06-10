package edu.aynu.qiuhaojie.client.products.service;

import edu.aynu.qiuhaojie.commons.beans.Products;

import java.util.List;

/**
 * (Products)表服务接口
 *
 * @author qhj
 * @since 2020-05-30 13:34:02
 */
public interface ProductsService {
    List<Products> findProductByCategory(String Category);

    List<Products> findProductsByName(String bookName);

    Products findProductById(Integer id);
}