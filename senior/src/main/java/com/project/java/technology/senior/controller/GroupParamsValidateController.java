package com.project.java.technology.senior.controller;

import com.project.java.technology.senior.model.ValidParamReqDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author za-yinshaobo at 2020/12/9 10:36
 * 分组校验
 */
@RestController
@RequestMapping("/validate/group")
public class GroupParamsValidateController {

    @PostMapping("/group1")
    public ValidParamReqDTO validParams1(@Validated(value = {ValidParamReqDTO.Group1.class}) ValidParamReqDTO reqDTO) {
        return reqDTO;
    }

    @PostMapping("/group2")
    public ValidParamReqDTO validParams2(@Validated(value = {ValidParamReqDTO.Group2.class}) ValidParamReqDTO reqDTO) {
        return reqDTO;
    }

    @PostMapping("/order")
    public ValidParamReqDTO validParams3(@Validated(value = {ValidParamReqDTO.GroupOrdered.class}) ValidParamReqDTO reqDTO) {
        return reqDTO;
    }
}
