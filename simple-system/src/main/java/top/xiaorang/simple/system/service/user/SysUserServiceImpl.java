package top.xiaorang.simple.system.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.xiaorang.simple.system.entity.user.SysUser;
import top.xiaorang.simple.system.repository.user.SysUserRepository;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    private final SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return sysUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误！"));
    }

    @Override
    public SysUser loadUserByPhone(String phone) {
        return sysUserRepository
                .findByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException("手机号错误！"));
    }

    @Override
    public Optional<SysUser> getUserByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }

    @Override
    public void save(SysUser sysUser) {
        sysUserRepository.save(sysUser);
    }
}
