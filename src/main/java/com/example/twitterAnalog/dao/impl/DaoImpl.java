package com.example.twitterAnalog.dao.impl;

import com.example.twitterAnalog.dao.Dao;
import com.example.twitterAnalog.domen.dto.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Slf4j
@Repository
@Transactional
public class DaoImpl extends JdbcDaoSupport implements Dao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }


    @Override
    public boolean isExistsNickname(String nickname) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM user WHERE nickname = ?);",
                Integer.class, nickname) != 0;
    }

    @Override
    public void insertNewUser(User user) {
        jdbcTemplate.update("INSERT INTO user(nickname,password,access_token) VALUES (?,?,?);",
        user.getNickname(), user.getEncryptPassword(), user.getAccessToken());
    }
}
