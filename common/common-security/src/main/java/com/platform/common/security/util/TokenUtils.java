package com.platform.common.security.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.platform.common.core.constant.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author szhua
 */
@Slf4j
@Component
public class TokenUtils {

    @Value("${spring.security.oauth2.jwt.signing-key}")
    private String signingKey;

    private static String key;

    private static final Integer AUTH_LENGTH = 7;

    /**
     * 获取token串
     *
     * @param token token
     * @return String
     */
    private static String getToken(String token) {
        if ((token != null) && (token.length() > AUTH_LENGTH)) {
            if (token.startsWith(SecurityConstants.BEARER)) {
                token = token.substring(token.indexOf(SecurityConstants.BEARER));
                return token;
            }
        }
        return token;
    }

    /**
     * 解析token
     *
     * @param token token串
     * @return Claims
     */
    public static Claims parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key.getBytes())
                    .parseClaimsJws(getToken(token))
                    .getBody();
        } catch (Exception e) {
            log.info("token验证失败:{} ==> {}", e.getMessage(), token);
        }
        return claims;
    }

    /**
     * 简单校验token
     *
     * @param token token
     * @return boolean
     */
    public static boolean validToken(String token) {
        Claims claims = parseToken(token);
        if (claims == null) {
            return Boolean.FALSE;
        }
        Date expiration = claims.getExpiration();
        return ObjectUtil.isNotNull(expiration) && System.currentTimeMillis() < expiration.getTime();
    }

    /**
     * 获取token
     *
     * @return token
     */
    public static String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JSONObject obj = JSONUtil.parseObj(authentication.getDetails());
        return obj.getStr(SecurityConstants.TOKEN_VALUE);
    }

    /**
     * 获取配置文件属性值
     */
    @PostConstruct
    public void set() {
        key = this.signingKey;
    }

}
