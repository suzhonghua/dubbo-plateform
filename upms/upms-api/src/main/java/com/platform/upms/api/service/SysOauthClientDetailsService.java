package com.platform.upms.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.upms.api.entity.SysOauthClientDetails;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
public interface SysOauthClientDetailsService extends IService<SysOauthClientDetails> {
	/**
	 * 通过ID删除客户端
	 *
	 * @param id
	 * @return
	 */
	boolean removeClientDetailsById(String id);

	/**
	 * 根据客户端信息
	 *
	 * @param sysOauthClientDetails
	 * @return
	 */
	boolean updateClientDetailsById(SysOauthClientDetails sysOauthClientDetails);
}
