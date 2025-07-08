package com.romi.my_dinnerdive.dao.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.romi.my_dinnerdive.dao.UserDao;
import com.romi.my_dinnerdive.dto.UserRegisterRequest;
import com.romi.my_dinnerdive.model.User;
import com.romi.my_dinnerdive.rowmapper.UserRowMapper;

@Component
public class UserDaoImpl implements UserDao{
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserById(Integer userId){
        String sql = "SELECT user_id, username, user_password, roles, created_date, last_modified_date " +
                     "FROM users WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username){
        String sql = "SELECT user_id, username, user_password, roles, created_date, last_modified_date " +
                     "FROM users WHERE username = :username";

        Map<String, Object> map = new HashMap<>();
        map.put("username", username);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
    String sql = "INSERT INTO users (username, user_password, roles, created_date, last_modified_date) " +
                 "VALUES (:username, :userPassword, :roles, :createdDate, :lastModifiedDate)";

    Map<String, Object> map = new HashMap<>();
    map.put("username", userRegisterRequest.getUsername());
    map.put("userPassword", userRegisterRequest.getUserPassword());
    map.put("roles", userRegisterRequest.getRoles().name());

    Date now = new Date();
    map.put("createdDate", now);
    map.put("lastModifiedDate", now);

    KeyHolder keyHolder = new GeneratedKeyHolder();

    namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

    int userId = keyHolder.getKey().intValue();

    return userId;
    }
}