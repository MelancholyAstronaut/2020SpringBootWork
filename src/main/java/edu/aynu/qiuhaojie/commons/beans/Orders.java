package edu.aynu.qiuhaojie.commons.beans;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

/**
 * (Orders)实体类
 *
 * @author makejava
 * @since 2020-05-31 16:49:48
 */
@Data
@Accessors(chain = true)
@Alias("order")
public class Orders implements Serializable {
    private static final long serialVersionUID = -30858334520002035L;
    
    private String id;
    
    private double money;
    
    private String receiverAddress;
    
    private String receiverName;
    
    private String receiverPhone;
    
    private Integer paystate;
    
    private Date ordertime;
    
    private User user;
}