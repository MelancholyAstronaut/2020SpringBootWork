package edu.aynu.qiuhaojie.commons.beans;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**-=
 * (Orders)实体类
 *
 * @author makejava
 * @since 2020-05-31 16:49:48
 */
@Data
@Accessors(chain = true)
public class OrderItem implements Serializable {
  private Orders order;
  private Products product;
  private int buynum;
}