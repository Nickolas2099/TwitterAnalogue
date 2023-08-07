package com.example.twitterAnalog.dao.impl;

import com.example.twitterAnalog.dao.Dao;
import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.dto.User;
import com.example.twitterAnalog.domain.response.exception.CommonException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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

    @Override
    public String getAccessToken(User user) {
         try {
            return jdbcTemplate.queryForObject("SELECT access_token FROM user WHERE nickname=? AND password = ?;",
                    String.class, user.getNickname(), user.getEncryptPassword());


         } catch(EmptyResultDataAccessException ex) {
             log.error(ex.toString());
             throw CommonException.builder().code(Code.USER_NOT_FOUND).message("Пользователь не найден")
                     .httpStatus(HttpStatus.BAD_REQUEST).build();
         }
    }
}
