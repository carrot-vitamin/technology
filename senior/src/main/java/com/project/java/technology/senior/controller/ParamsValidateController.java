package com.project.java.technology.senior.controller;

import com.alibaba.fastjson.JSONObject;
import com.project.java.technology.senior.model.GroupValidParamReqDTO;
import com.project.java.technology.senior.model.ValidParamReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author ShaoBo Yin at 2020/12/9 10:36
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


    /** 分组校验、顺序校验 **/

    @PostMapping("/group1")
    public GroupValidParamReqDTO validParams1(@Validated(value = {GroupValidParamReqDTO.Group1.class}) GroupValidParamReqDTO reqDTO) {
        return reqDTO;
    }

    @PostMapping("/group2")
    public GroupValidParamReqDTO validParams2(@Validated(value = {GroupValidParamReqDTO.Group2.class}) GroupValidParamReqDTO reqDTO) {
        return reqDTO;
    }

    @PostMapping("/order")
    public GroupValidParamReqDTO validParams3(@Validated(value = {GroupValidParamReqDTO.GroupOrdered.class}) GroupValidParamReqDTO reqDTO) {
        return reqDTO;
    }

    /**
     * 嵌套校验
     */
    @PostMapping("/innerObjValid")
    public ValidParamReqDTO innerObjValid(@Validated @RequestBody ValidParamReqDTO reqDTO) {
        return reqDTO;
    }

    /**
     * 编程式参数校验
     */
    @Autowired
    private Validator validator;

    @PostMapping("/validParams5")
    public Object validParams5(@RequestBody ValidParamReqDTO reqDTO) {
        Set<ConstraintViolation<ValidParamReqDTO>> validate = this.validator.validate(reqDTO);
        if (validate.isEmpty()) {
            return reqDTO;
        } else {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<ValidParamReqDTO> v : validate) {
                builder.append(v.getPropertyPath()).append(":").append(v.getMessage()).append("; ");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "999999");
            jsonObject.put("message", builder.toString());
            return jsonObject;
        }
    }
}
