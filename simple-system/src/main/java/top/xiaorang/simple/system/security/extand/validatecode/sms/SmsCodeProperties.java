package top.xiaorang.simple.system.security.extand.validatecode.sms;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.xiaorang.simple.system.security.extand.validatecode.ValidateCodeProperties;

@EqualsAndHashCode(callSuper = true)
@Data
@Configuration
@ConfigurationProperties(prefix = "simple.security.sms-code")
public class SmsCodeProperties extends ValidateCodeProperties {}
