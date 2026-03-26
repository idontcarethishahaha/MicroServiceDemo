package org.example.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import jakarta.annotation.PostConstruct;
import org.example.component.SentinelGatewayProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.*;

/**
 * 类说明：流控哨兵配置类
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026/3/26 20:35
 */
@Configuration
public class SentinelConfig {
    private final List<ViewResolver> viewResolvers;//视图解析器列表
    private final ServerCodecConfigurer serverCodecConfigurer;
    private final SentinelGatewayProperties gatewayProperties;//网关限流相关配置

    //alt+insert 构造器，Fn灭灯
   // 声明构造器，用于属性的初始化
    public SentinelConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                          ServerCodecConfigurer serverCodecConfigurer,
                          SentinelGatewayProperties gatewayProperties) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
        this.gatewayProperties = gatewayProperties;
    }
    // 内置一个网关流量哨兵的过滤器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler(){
        return new SentinelGatewayBlockExceptionHandler(viewResolvers,serverCodecConfigurer);
    }
    // 定义限流提示的默认消息
    private final String BLOCK_MESSAGE = "请求超过网关处理上限，拒绝处理本次请求！";

    // 在执行过滤器之前，对限流时响应的内容和格式进行设置
    @PostConstruct
    public void initExceptionResponse(){
        GatewayCallbackManager.setBlockHandler((exchange,ex)->
            ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(Map.of("code",HttpStatus.TOO_MANY_REQUESTS,"message",BLOCK_MESSAGE)))
        );
    }

    @PostConstruct
    public void initGatewaySentinelRules(){
        Set<GatewayFlowRule> rules = new HashSet<>();
        for(SentinelGatewayProperties.GatewayGroup group : gatewayProperties.getGroups()) {
            // 1. 先创建单个规则对象
            GatewayFlowRule rule = new GatewayFlowRule(group.getName());
            // 2. 给单个规则设置参数（直接调用 rule.xxx）
            rule.setCount(group.getCount());
            rule.setIntervalSec(group.getIntervalSec());
            // 3. 将规则加入集合
            rules.add(rule);
        }
        GatewayRuleManager.loadRules(rules);
    }

    /* 在执行过滤器之前，对API请求进行分组，方法名随意 */
    @PostConstruct
    public void initApiDefinition() {
        // 初始化一个空的API定义集合，用于存储所有分组的API定义
        Set<ApiDefinition> definitions = new HashSet<>();
        // 遍历配置文件中的所有分组
        for (SentinelGatewayProperties.GatewayGroup group : gatewayProperties.getGroups()) {
            // 创建一个新的API定义，名称为分组名称
            ApiDefinition apiDefinition = new ApiDefinition(group.getName());
            // 创建一个新的路径预测项，并设置路径预测项的路径模式为分组的路径匹配规则
            ApiPathPredicateItem predicateItem = new ApiPathPredicateItem();
            predicateItem.setPattern(group.getPattern());
            // 转换匹配策略（配置文件中1对应前缀匹配，0对应精确匹配）
            // 如果匹配策略为1（前缀匹配），则设置Sentinel网关常量为前缀匹配策略，否则，设置为精确匹配策略
            predicateItem.setMatchStrategy(group.getMatchStrategy() == 1 ? SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX : SentinelGatewayConstants.URL_MATCH_STRATEGY_EXACT);
            // 将路径预测项添加到API定义的预测项集合中
            apiDefinition.setPredicateItems(Collections.singleton(predicateItem));
            // 将API定义添加到集合中
            definitions.add(apiDefinition);
        }
        // 加载所有分组的API定义到Sentinel网关中
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }
}
