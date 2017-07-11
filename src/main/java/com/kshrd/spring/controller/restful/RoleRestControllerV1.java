package com.kshrd.spring.controller.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshrd.spring.service.RoleService;
import com.kshrd.spring.entity.*;
import com.kshrd.spring.response.HttpMessage;
import com.kshrd.spring.response.ResponseHttpStatus;
import com.kshrd.spring.response.ResponseList;
import com.kshrd.spring.response.Table;
import com.kshrd.spring.response.Transaction;
import com.kshrd.spring.response.failure.ResponseListFailure;

@RestController
@RequestMapping("/v1/api/role")
public class RoleRestControllerV1 {

	RoleService roleService;
    HttpStatus httpStatus = HttpStatus.OK;

    @Autowired
    public RoleRestControllerV1(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping
    public ResponseEntity<ResponseList<Role>> findAllRole(){
        ResponseList<Role> responseList = new ResponseList<>();
        try{
        	httpStatus = HttpStatus.OK;
            List<Role> roles = roleService.findAllRole();
            if(!roles.isEmpty()){
                responseList = new ResponseList<Role>(HttpMessage.success(Table.ROLES, Transaction.Success.RETRIEVE),
                        true, roles, null);
            }
            else{
                httpStatus = HttpStatus.NOT_FOUND;
                responseList = new ResponseListFailure<>(HttpMessage.fail(Table.ROLES, Transaction.Fail.RETRIEVE), false,
                        ResponseHttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            e.printStackTrace();
            httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
            responseList = new ResponseListFailure<>(HttpMessage.fail(Table.ROLES, Transaction.Fail.RETRIEVE), false,
                    ResponseHttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ResponseList<Role>>(responseList, httpStatus);
    }
	
}
