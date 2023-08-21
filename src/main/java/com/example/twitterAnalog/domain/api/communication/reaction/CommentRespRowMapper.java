package com.example.twitterAnalog.domain.api.communication.reaction;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRespRowMapper implements RowMapper<CommentResp> {


    @Override
    public CommentResp mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CommentResp.builder()
                .commentId(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .nickname(rs.getString("nickname"))
                .text(rs.getString("text"))
                .timeInsert(rs.getString("time_insert"))
                .build();
    }
}
