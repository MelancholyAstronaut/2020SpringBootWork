package edu.aynu.qiuhaojie.client.products.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.aynu.qiuhaojie.commons.beans.Products;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * (Products)表数据库访问层
 *
 * @author qhj
 * @since 2020-05-30 13:34:01
 */
@Mapper
@Component
public interface ProductsDao extends BaseMapper<Products> {
    List<Products> findProductByCategory(String Category);
}