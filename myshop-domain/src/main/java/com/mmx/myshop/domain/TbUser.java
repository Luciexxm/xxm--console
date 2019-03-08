package com.mmx.myshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mmx.myshop.common.utils.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
public class TbUser extends BaseEntity implements Serializable { ;
    @NotBlank(message = "用户名不能为空")
    private String username;

    @JsonIgnore
    private String password;

    @Size(min = 11, max = 20, message = "手机格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL, message = "邮箱格式不正确")
    private String email;


    //扩展属性
    private String rememberMe;


}
