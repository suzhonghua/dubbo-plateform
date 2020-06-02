package com.platform.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.core.constant.CacheConstants;
import com.platform.upms.api.entity.SysOauthClientDetails;
import com.platform.upms.api.service.SysOauthClientDetailsService;
import com.platform.upms.mapper.SysOauthClientDetailsMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author szhua
 * @since 2019/2/1
 */
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails>
        implements SysOauthClientDetailsService {

    /**
     * 通过ID删除客户端
     *
     * @param id
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#id")
    public boolean removeClientDetailsById(String id) {
        return this.removeById(id);
    }

    /**
     * 根据客户端信息
     *
     * @param clientDetails
     * @return
     */
    @Override
    @CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
    public boolean updateClientDetailsById(SysOauthClientDetails clientDetails) {
        return this.updateById(clientDetails);
    }
}
