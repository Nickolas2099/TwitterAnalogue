package com.example.twitterAnalog.domain.api.common;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRespRowMapper implements RowMapper<UserResp> {

    @Override
    public UserResp mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserResp.builder()
                .userId(rs.getLong("id"))
                .nickname(rs.getString("nickname"))
                .build();
    }
}
