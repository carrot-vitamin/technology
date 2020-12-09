package com.project.java.technology.senior.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author za-yinshaobo at 2020/12/9 11:17
 * 参数校验
 */
@Data
public class ValidParamReqDTO {

    public interface Group1 {}

    public interface Group2 {}

    @GroupSequence(value = {Group2.class, Group1.class})
    public interface GroupOrdered {
        // 按顺序校验，先校验Group2，再校验Group1
    }

    @NotNull(message = "aBoolean 不允许为空")
    @AssertFalse(message = "aBoolean 必须是false")
    private Boolean aBoolean;

    /**
     * 如果两个不同的接口，一个需要限制最大值为100，一个需要限制最大值为80，则需要使用分组校验
     * 注意：@Validated 注解中加上groups属性后,DTO中没有加group属性的参数校验规则将失效
     * 注意：加了groups属性之后，必须在@Validated 注解中也加上groups属性，校验规则才能生效，不然下面的校验限制就失效了
     */
    @DecimalMax(value = "80", groups = {Group1.class}, message = "strMax 必须为数值类型，且最大值为80")
    @DecimalMax(value = "100", groups = {Group2.class}, message = "strMax 必须为数值类型，且最大值为100")
    private String strMax;

    @Digits(integer = 2, fraction = 3, message = "decimalStr 整数部分限制最大2位精度，小数部分限制最大3位精度")
    private BigDecimal decimalStr;

    @Email(message = "email 必须为邮箱格式")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "futureOrPresent 必须为将来或现在的时间")
    private Date futureOrPresent;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent(message = "pastOrPresent 必须为过去或现在的时间")
    private Date pastOrPresent;

    @Size(min = 1, max = 2, message = "listSize 集合长度为1~2之间")
    private List<Integer> listSize;

    @CreditCardNumber(message = "creditCard 必须为信用卡格式")
    private String creditCard;

    @Length(min = 2, max = 3, message = "字符串长度必须要2~3之间")
    private String str;
}
