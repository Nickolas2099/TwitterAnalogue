package com.example.twitterAnalog.dao.common;

import com.example.twitterAnalog.dao.common.CommonDao;
import com.example.twitterAnalog.domain.api.common.TagResp;
import com.example.twitterAnalog.domain.api.common.TagRespRowMapper;
import com.example.twitterAnalog.domain.api.communication.reaction.CommentResp;
import com.example.twitterAnalog.domain.api.communication.reaction.CommentRespRowMapper;
import com.example.twitterAnalog.domain.constant.Code;
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
import java.util.List;

@Repository
@Slf4j
@Transactional
public class CommonDaoImpl extends JdbcDaoSupport implements CommonDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<TagResp> getTagsByPhraseId(long phraseId) {
        return jdbcTemplate.query("SELECT text FROM tag WHERE id IN (SELECT tag_id FROM phrase_tag WHERE phrase_id=?);",
                new TagRespRowMapper(), phraseId);
    }
    @Override
    public long getUserIdByToken(String accessToken) {
        try{
            return jdbcTemplate.queryForObject("SELECT id FROM user WHERE access_token = ?;", Long.class, accessToken);
        } catch(EmptyResultDataAccessException ex) {
            log.error(ex.toString());
            throw CommonException.builder().code(Code.AUTHORIZATION_ERROR).userMessage("Ошибка авторизации")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public long getCountLikes(long phraseId) {
        try {
            return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM like_phrase WHERE phrase_id = ?;", Long.class, phraseId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public void testSchedulerLock(String instanceName) {
        jdbcTemplate.update("INSERT INTO schedlock(name) VALUES (?);", instanceName);
    }

    @Override
    public List<CommentResp> getCommentsByPhraseId(long phraseId) {
        try {

            return jdbcTemplate.query("SELECT comment.id AS comment_Id, user_id, nickname, text, " +
                    "comment.time_insert AS time_insert" +
                    "FROM comment " +
                    "   JOIN user u on u.id = comment.user_id " +
                    "WHERE phrase_id = ? " +
                    "ORDER BY comment.time_insert DESC;", new CommentRespRowMapper(), phraseId);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
