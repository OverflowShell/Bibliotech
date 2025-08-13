/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.utils;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import org.slf4j.LoggerFactory;

public class LogbackConfigurator {
    public static void initialize() {
        try {
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            context.reset(); // Limpiar configuración existente

            // 1. Configurar appender de archivo con rotación
            RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
            fileAppender.setContext(context);
            fileAppender.setName("FILE");
            fileAppender.setFile("logs/ilib-app.log"); // Archivo actual
            
            // Política de rotación diaria
            TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new TimeBasedRollingPolicy<>();
            rollingPolicy.setContext(context);
            rollingPolicy.setParent(fileAppender);
            rollingPolicy.setFileNamePattern("logs/ilib-app.%d{yyyy-MM-dd}.log");
            rollingPolicy.setMaxHistory(7); // Conservar 7 días de logs
            rollingPolicy.start();
            
            fileAppender.setRollingPolicy(rollingPolicy);
            
            // Configurar el formato del log
            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setContext(context);
            encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
            encoder.start();
            
            fileAppender.setEncoder(encoder);
            fileAppender.start();
            
            // 2. Configurar logger raíz
            Logger rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);
            rootLogger.detachAndStopAllAppenders(); // Eliminar appenders por defecto
            rootLogger.addAppender(fileAppender);
            
            // 3. Configurar nivel de log para tu aplicación
            Logger appLogger = context.getLogger("com.mycompany");
            appLogger.setLevel(ch.qos.logback.classic.Level.DEBUG);
            
        } catch (Exception e) {
            System.err.println("Error configuring Logback: " + e.getMessage());
            e.printStackTrace();
        }
    }
}