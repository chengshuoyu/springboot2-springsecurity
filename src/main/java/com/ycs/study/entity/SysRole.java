package com.ycs.study.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {
    private Long id;
    private String roleName;
    public SysRole(Long id,String roleName){
    	this.id=id;
    	this.roleName=roleName;
    }
}
