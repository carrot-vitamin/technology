package com.project.java.technology.senior.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.java.technology.senior.annotation.MyNotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
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

    @MyNotBlank(message = "自定义 不能为空")
    private String myValidator;

    @NotNull(message = "不允许为空")
    @AssertFalse(message = "必须是false")
    private Boolean aBoolean;

    @DecimalMax(value = "100", message = "必须为数值类型，且最大值为100")
    private String strMax;

    @Digits(integer = 2, fraction = 3, message = "整数部分限制最大2位精度，小数部分限制最大3位精度")
    private BigDecimal decimalStr;

    @Email(message = "必须为邮箱格式")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @FutureOrPresent(message = "必须为将来或现在的时间")
    private Date futureOrPresent;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @PastOrPresent(message = "必须为过去或现在的时间")
    private Date pastOrPresent;

    @Size(min = 1, max = 2, message = "集合长度为1~2之间")
    private List<Integer> listSize;

    @Size(min = 1, max = 2, message = "字符串长度为1~2之间")
    private String strSize;

    @CreditCardNumber(message = "必须为信用卡格式")
    private String creditCard;

    @Length(min = 2, max = 3, message = "字符串长度必须要2~3之间")
    private String str;

    /**
     * 嵌套验证必须用@Valid
     */
    @Valid
    @NotNull(message = "对象不允许为空")
    private InnerClass innerClass;

    @Data
    static class InnerClass {
        @NotBlank(message = "不允许为空")
        private String str;
    }
}
