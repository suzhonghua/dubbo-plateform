package com.platform.auth.enhancer;

import com.platform.common.core.constant.CommonConstants;
import com.platform.common.core.constant.ResultConstants;
import com.platform.common.core.constant.SecurityConstants;
import com.platform.common.security.entity.ScUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author szhua
 */
public class JwtTokenEnhancer implements TokenEnhancer {
    /**
     * Provides an opportunity for customization of an access token (e.g. through its additional information map) during
     * the process of creating a new token for use by a client.
     *
     * @param accessToken    the current access token with its expiration and refresh token
     * @param authentication the current authentication including client and user details
     * @return a new token enhanced with additional information
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>(4);
        ScUser scUser = (ScUser) authentication.getUserAuthentication().getPrincipal();
        additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.PROJECT_LICENSE);
        additionalInfo.put(SecurityConstants.DETAILS_USER_ID, scUser.getId());
        additionalInfo.put(SecurityConstants.DETAILS_USERNAME, scUser.getUsername());
        additionalInfo.put(SecurityConstants.DETAILS_DEPT_ID, scUser.getDeptId());
        additionalInfo.put(ResultConstants.CODE, CommonConstants.SUCCESS);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
