package org.example.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * 类说明：授权规则设置
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/22 14:46
 */
@Component
public class AuthConfig implements RequestOriginParser {
    private final String authFlag="role";
    // ctrl + i
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 获取到授权相关参数值
        String paramVal=httpServletRequest.getParameter(authFlag);
        if(paramVal==null){//如果从请求参数中获取失败,就去请求头中获取
            paramVal=httpServletRequest.getHeader(authFlag);
        }
        return paramVal;
    }
}
