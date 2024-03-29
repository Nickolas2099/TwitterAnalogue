package com.example.twitterAnalog.domain.api.common;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhraseRespRowMapper implements RowMapper<PhraseResp> {


    @Override
    public PhraseResp mapRow(ResultSet row, int rowNum) throws SQLException {
        return PhraseResp.builder()
                .phraseId(row.getLong("phrase_id"))
                .text(row.getString("text"))
                .timeInsert(row.getString("time_insert"))
                .build();
    }
}
