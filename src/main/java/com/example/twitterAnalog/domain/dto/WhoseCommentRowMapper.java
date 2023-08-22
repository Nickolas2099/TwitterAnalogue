package com.example.twitterAnalog.domain.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WhoseCommentRowMapper implements RowMapper<WhoseComment> {


    @Override
    public WhoseComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return WhoseComment.builder()
                .commentUserId(rs.getLong("comment_user_id"))
                .phraseUserId(rs.getLong("phrase_user_id"))
                .build();
    }
}
