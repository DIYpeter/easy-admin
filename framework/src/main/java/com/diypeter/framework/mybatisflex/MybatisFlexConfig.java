package com.diypeter.framework.mybatisflex;

import com.mybatisflex.core.audit.AuditManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author: diypeter
 * @date: 2024/9/11 16:35
 */
@Slf4j
@Configuration
public class MybatisFlexConfig {

    public MybatisFlexConfig() {
        // 开启审计功能
        AuditManager.setAuditEnable(true);

        // 设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> {
                    log.info("[SQL] {} ", auditMessage.getFullSql());
                    log.info("[SQL] 耗时 {} ms ", auditMessage.getElapsedTime());
                }
        );
    }

}
