package org.example.job;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 类说明：xxl-job执行器
 *
 * @author WuWenJin
 * @version 1.0
 * @date 2026-04-19 9:17
 */
@Component
@Slf4j
public class XxlJobExecutor {

    @XxlJob(value="sendMessage",init="init",destroy="destroy")
    public void sendMessage(){
        XxlJobHelper.log("这是一条xxl-job日志：指定用户发送一条短信");
        // 从任务中获取一个参数
        int userId = Integer.parseInt(XxlJobHelper.getJobParam());
        // 假设有效的用户ID是1-10
        if (userId > 0 && userId <= 10) {
            String msg="执行成功！向用户【"+userId+"】发送了一条营销短信！";
            log.info(msg);
            XxlJobHelper.handleSuccess(msg);
        }else{
            String msg="访问失败,"+userId+"不存在！";
            log.warn(msg);
            XxlJobHelper.handleFail(msg);
        }
    }
    private void init(){
        log.info("初始化完毕~");
    }
    private void destroy(){
        log.info("执行结束，当前实例将被销毁~");
    }
}
