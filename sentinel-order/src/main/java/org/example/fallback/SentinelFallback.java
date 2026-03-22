package org.example.fallback;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * 类说明：哨兵默认执行的降级类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/19 20:38
 */
@Slf4j
public class SentinelFallback {

    public static String paramFallback(String name,Integer age,Throwable e){
        String exceptionMsg = e.getMessage();
        if(e instanceof ParamFlowException){
            log.warn("参数:name={},age={}",name,age);
            exceptionMsg = "您访问的太快或当前访问人数过多，请稍后重试~";
        }
        return exceptionMsg;
    }
    public static String fallback(Throwable e) {
        // 获取sentinel抛出的异常消息
        String exceptionMessage = e.getMessage();
        if (e instanceof FlowException) {//限流异常
            return "当前访问网站的用户人数过多，请稍后重试~";
        }
        if (e instanceof DegradeException){//降级异常
            return "当前服务响应的错误次数或频率较高，请稍后再试~";
        }
        if (e instanceof AuthorityException){//权限异常
            return "你没有访问当前资源的权限~";
        }
        if (e instanceof SystemBlockException){//系统限制异常
            return "当前系统存在访问限制，请练习管理员~";
        }
        if (e instanceof ParamFlowException){
            return "参数流控超过上限，请稍后再试~";
        }
        return "系统错误："+exceptionMessage;
    }
}
