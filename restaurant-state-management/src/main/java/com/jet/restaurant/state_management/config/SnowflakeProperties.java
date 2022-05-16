package com.jet.restaurant.state_management.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SnowflakeProperties {

    @Value("${snowflake.machine-id}")
    private long machineId;

    @Value("${snowflake.dataCenter-id}")
    private long dataCenterId;
}
