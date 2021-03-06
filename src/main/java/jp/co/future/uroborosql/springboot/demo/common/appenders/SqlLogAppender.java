package jp.co.future.uroborosql.springboot.demo.common.appenders;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * SqlLogAppender
 *
 * @author Kenichi Hoshi
 */
@Component
@EnableAutoConfiguration
public class SqlLogAppender extends AppenderBase<ILoggingEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(SqlLogAppender.class);

    @Value("${petclinic.send-sqllog}")
    private Boolean sendSqllog;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private Layout<ILoggingEvent> layout;

    public void setLayout(final Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        simpMessagingTemplate.convertAndSend("/topic/sqllog", iLoggingEvent.getFormattedMessage());
    }

    @PostConstruct
    public void init() {
        LOG.info("Send SQL log: " + sendSqllog);
        if (!sendSqllog) {
            return;
        }
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<String> loggerNames = Arrays.asList("jp.co.future.uroborosql.AbstractAgent", "jp.co.future.uroborosql.filter.DebugSqlFilter");
        context.getLoggerList().stream()
            .filter(logger -> loggerNames.contains(logger.getName()))
            .forEach(logger -> logger.addAppender(SqlLogAppender.this));
        setContext(context);
        start();
    }
}
