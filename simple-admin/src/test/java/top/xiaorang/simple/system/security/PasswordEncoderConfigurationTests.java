package top.xiaorang.simple.system.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderConfigurationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordEncoder() {
        String encodedPassword = passwordEncoder.encode("123456");
        System.out.println(encodedPassword);
        System.out.println(passwordEncoder.matches("123456", encodedPassword));
    }
}
