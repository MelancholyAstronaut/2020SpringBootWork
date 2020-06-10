package edu.aynu.qiuhaojie.commons.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author qhj
 * @since 2020-05-24 20:51:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Alias("user")
public class User implements Serializable {
    private static final long serialVersionUID = 600077474977862697L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String gender;

    private String email;

    private String telephone;

    private String introduce;

    private String activecode;

    private Integer state;

    private String role;

    private Date registtime;
}