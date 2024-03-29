package com.example.twitterAnalog.domain.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhraseRowMapper implements RowMapper<Phrase> {
//с помощью этого класса можно распределять строки таблицы по полям класса
    @Override
    public Phrase mapRow(ResultSet row, int rowNum) throws SQLException {
        return Phrase.builder()
                .id(row.getLong("id"))
                .userId(row.getLong("user_id"))
                .text(row.getString("text"))
                .timeInsert(row.getString("time_insert"))
                .build();
    }
}
