package com.ycs.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * <p>com.ycs.study.entity</p>
 * <p>ClassName: SysUser</p>
 * <p>Description: TODO</p>
 * <p>Author: Chengshuo Yu</p>
 * <p>Date: 2019/9/27 17:05</p>
 * <p>Version: v1.0</p>
 */
@Data
@AllArgsConstructor
public class SysUser {
    private Long id;
    private String userName;
    private String password;
    private List<String> roles;
}
