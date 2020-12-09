package com.project.java.technology.senior.controller;

import com.project.java.technology.senior.model.ValidParamReqDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author za-yinshaobo at 2020/12/9 10:36
 */
@Validated
@RestController
@RequestMapping("/validate")
public class ParamsValidateController {

    /**
     * 注意：如果想在参数中使用 @NotNull 这种注解校验，就必须在类上添加 @Validated
     */
    @GetMapping("/queryId")
    public String sayName(@RequestParam(value = "id") @NotNull(message = "ID不允许为空") Integer id) {
        return "ID is " + id;
    }

    /**
     * 注意：如果方法中的参数是对象类型，则必须要在参数对象前面添加 @Validated
     */
    @PostMapping("/validParams")
    public ValidParamReqDTO validParams(@Validated ValidParamReqDTO reqDTO) {
        return reqDTO;
    }
}