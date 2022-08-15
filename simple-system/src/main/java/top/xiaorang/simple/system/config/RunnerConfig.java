package top.xiaorang.simple.system.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.xiaorang.simple.common.enums.Gender;
import top.xiaorang.simple.system.entity.user.SysUser;
import top.xiaorang.simple.system.service.user.SysUserService;

/**
 * @author liulei
 * @version 1.0
 * @since 2022/8/15 17:31
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class RunnerConfig {
    private static final String MANAGER_USER_NAME = "admin";
    private static final String MANAGER_NICKNAME_NAME = "管理员";
    private static final String SYSTEM_INITIAL_PASSWORD = "123456";
    private final SysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initDatabase() {
        log.debug("项目启动~~初始化数据库");
        return args -> createManager();
    }

    private void createManager() {
        log.debug("创建管理员账号ing...");
        SysUser manager = sysUserService.getUserByUsername(MANAGER_USER_NAME).orElse(null);
        if (manager != null) {
            log.debug("Er..已存在管理员账号，不需要再创建啦~");
            return;
        }
        String encodedPassword = passwordEncoder.encode(SYSTEM_INITIAL_PASSWORD);
        SysUser sysUser = new SysUser(MANAGER_USER_NAME, MANAGER_NICKNAME_NAME, encodedPassword, Gender.MALE, true, "13838384388");
        sysUserService.save(sysUser);
        log.debug("Whoopee..管理员账号创建成功啦，现在可以使用管理员账号【账号：admin，密码：123456】登录");
    }
}
