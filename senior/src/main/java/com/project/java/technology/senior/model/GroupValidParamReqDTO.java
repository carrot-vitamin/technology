package com.project.java.technology.senior.model;

import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMax;

/**
 * @author za-yinshaobo at 2020/12/9 11:17
 * 参数校验
 */
@Data
public class GroupValidParamReqDTO {

    public interface Group1 {}

    public interface Group2 {}

    @GroupSequence(value = {Group2.class, Group1.class})
    public interface GroupOrdered {
        // 按顺序校验，先校验Group2，再校验Group1
    }

    /**
     * 如果两个不同的接口，一个需要限制最大值为100，一个需要限制最大值为80，则需要使用分组校验
     * 注意：@Validated 注解中加上groups属性后,DTO中没有加group属性的参数校验规则将失效
     * 注意：加了groups属性之后，必须在@Validated 注解中也加上groups属性，校验规则才能生效，不然下面的校验限制就失效了
     */
    @DecimalMax(value = "80", groups = {Group1.class}, message = "strMax 必须为数值类型，且最大值为80")
    @DecimalMax(value = "100", groups = {Group2.class}, message = "strMax 必须为数值类型，且最大值为100")
    private String strMax;
}
