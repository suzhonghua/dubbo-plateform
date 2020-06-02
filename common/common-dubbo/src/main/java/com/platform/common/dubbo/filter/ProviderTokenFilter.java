package com.platform.common.dubbo.filter;

import com.platform.common.core.constant.SecurityConstants;
import com.platform.common.security.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;
import org.springframework.security.access.AccessDeniedException;

/**
 * @author szhua
 * <p>
 * 提供方 验证token
 * </p>
 */
@Slf4j
public class ProviderTokenFilter implements Filter {
    /**
     * Make sure call invoker.invoke() in your implementation.
     *
     * @param invoker
     * @param invocation
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String paramToken = RpcContext.getContext().getAttachment(SecurityConstants.ACCESS_TOKEN);
        if (SecurityConstants.INNER_TOKEN.equals(paramToken) || TokenUtils.validToken(paramToken)) {
            return invoker.invoke(invocation);
        }
        log.warn("访问接口 {} 没有权限", invocation.getMethodName());
        throw new AccessDeniedException("Access is denied");
    }
}
