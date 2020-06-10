package edu.aynu.qiuhaojie.commons.beans;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * (Products)实体类
 *
 * @author qhj
 * @since 2020-05-30 13:33:58
 */
@Data
@Alias("product")
@Accessors(chain = true)
public class Products implements Serializable {
    private static final long serialVersionUID = 501828072321797580L;
    
    private String id;
    
    private String name;
    
    private double price;
    
    private String category;
    
    private Integer pnum;
    
    private String imgurl;
    
    private String description;

}