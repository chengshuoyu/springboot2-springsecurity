package com.ycs.study.service;

import com.ycs.study.entity.SysUser;

/**
 * <p>com.ycs.study.service</p>
 * <p>InterfaceName: IUserService</p>
 * <p>Description: TODO</p>
 * <p>Author: Chengshuo Yu</p>
 * <p>Date: 2019/9/27 17:06</p>
 * <p>Version: v1.0</p>
 */
public interface IUserService {
    SysUser findByUsername(String userName);
}
