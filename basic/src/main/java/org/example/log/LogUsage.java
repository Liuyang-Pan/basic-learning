package org.example.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUsage {
    public static Logger LOG = LoggerFactory.getLogger("LogUsage.class");

    public static void main(String[] args) {
        LOG.info("测试日志");
    }
}
