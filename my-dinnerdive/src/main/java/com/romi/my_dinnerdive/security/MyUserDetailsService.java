package com.romi.my_dinnerdive.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.romi.my_dinnerdive.model.User;
import com.romi.my_dinnerdive.repository.MemberRepo;

/**
 * MyUserDetailsService 實作 UserDetailsService，
 * 用來根據帳號從資料庫載入使用者資訊，提供給 Spring Security 認證流程使用。
 */
@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private MemberRepo memberRepo;

    /**
     * 根據帳號查詢使用者，並回傳封裝過的 MyUser 給 Spring Security。
     *
     * @param username 使用者帳號
     * @return 實作 UserDetails 的 MyUser 物件
     * @throws UsernameNotFoundException 若帳號不存在時拋出例外
     */
    @Override
    public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = memberRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("帳號不存在：" + username);
        }

        return new MyUser(user);
    }
}
