package com.cbs.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Log {
    static final Logger logger = LoggerFactory.getLogger(Log.class);
    public static void main(String[] args) {
        logger.info("Info Level.");
        logger.warn("Warn Level.");
        logger.error("Error Level.");


    }
}
