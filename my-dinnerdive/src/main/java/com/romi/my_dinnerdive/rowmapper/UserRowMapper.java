package com.romi.my_dinnerdive.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.romi.my_dinnerdive.constant.UserCategory;
import com.romi.my_dinnerdive.model.User;

public class UserRowMapper implements RowMapper<User>{
    
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setAccount(resultSet.getString("account"));
        
        String rolesStr = resultSet.getString("roles");
        UserCategory roles = UserCategory.valueOf(rolesStr);
        user.setRoles(roles);

        user.setMemberPassword(resultSet.getString("member_password"));
        user.setCreatedDate(resultSet.getTimestamp("created_date"));
        user.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return user;
    }
}
