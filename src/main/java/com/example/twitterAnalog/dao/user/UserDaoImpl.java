package com.example.twitterAnalog.dao.user;

import com.example.twitterAnalog.dao.user.UserDao;
import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.dto.User;
import com.example.twitterAnalog.domain.entity.Phrase;
import com.example.twitterAnalog.domain.entity.PhraseRowMapper;
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

@Slf4j
@Repository
@Transactional
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

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
             throw CommonException.builder().code(Code.USER_NOT_FOUND).userMessage("Пользователь не найден")
                     .httpStatus(HttpStatus.BAD_REQUEST).build();
         }
    }


    @Override
    public void addTag(String tag) {
        jdbcTemplate.update("INSERT INTO tag(text) SELECT DISTINCT LOWER(?) FROM tag WHERE NOT EXISTS " +
                "(SELECT text FROM tag WHERE text = LOWER(?));", tag, tag);
    }

    @Override
    public void addPhraseTag(long phraseId, String tag) {

        jdbcTemplate.update("INSERT IGNORE INTO phrase_tag(phrase_id, tag_id)" +
                " VALUES (?, (SELECT id FROM tag WHERE text = LOWER(?)));", phraseId, tag);
    }

    @Override
    public long addPhrase(long userId, String text) {
        jdbcTemplate.update("INSERT INTO phrase(user_id, text) VALUES (?,?);", userId, text);
        return jdbcTemplate.queryForObject("SELECT id FROM phrase WHERE id = LAST_INSERT_ID();", Long.class);
    }

    @Override
    public List<Phrase> getPhrasesByUserId(long userId) {
        return jdbcTemplate.query("SELECT * FROM phrase WHERE user_id = ? ORDER BY time_insert DESC;",
                new PhraseRowMapper(), userId);
    }

}
