package edu.aynu.qiuhaojie.client.products.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.aynu.qiuhaojie.client.products.dao.ProductsDao;
import edu.aynu.qiuhaojie.client.products.service.ProductsService;
import edu.aynu.qiuhaojie.commons.beans.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Products)表服务实现类
 *
 * @author qhj
 * @since 2020-05-30 13:34:02
 */
@Service("ProductsService")
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    private ProductsDao productsDao;

    @Override
    public List<Products> findProductByCategory(String Category) {
        return productsDao.findProductByCategory(Category);
    }

    @Override
    public List<Products> findProductsByName(String bookName) {
        QueryWrapper qw = new QueryWrapper();
        qw.like("name", bookName);

        qw.ne("name", "请输入书名");

        return productsDao.selectList(qw);
    }

    @Override
    public Products findProductById(Integer id) {
        return productsDao.selectById(id);

    }
}