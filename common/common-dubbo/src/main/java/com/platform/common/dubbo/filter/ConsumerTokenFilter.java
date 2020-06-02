package com.platform.common.dubbo.filter;

import cn.hutool.core.util.StrUtil;
import com.platform.common.core.constant.SecurityConstants;
import com.platform.common.security.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;

/**
 * @author szhua
 * <p>
 * 消费方 传递token
 * </p>
 */
@Slf4j
public class ConsumerTokenFilter implements Filter {
    /**
     * Make sure call invoker.invoke() in your implementation.
     *
     * @param invoker
     * @param invocation
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String token = TokenUtils.getToken();
        if (StrUtil.isBlank(token)) {
            // 携带inner token  内部访问传递inner token
            Object[] arguments = invocation.getArguments();
            if (arguments.length > 0) {
                // 内部访问 token参数 放最后一个
                token = String.valueOf(arguments[arguments.length - 1]);
            }
        }
        RpcContext.getContext().setAttachment(SecurityConstants.ACCESS_TOKEN, token);
        log.info("dubbo调用: {}", invoker.getUrl());
        return invoker.invoke(invocation);
    }
}
