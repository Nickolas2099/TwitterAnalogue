package com.example.twitterAnalog.dao.communication;

import com.example.twitterAnalog.domain.api.communication.reaction.CommentPhraseReq;
import com.example.twitterAnalog.domain.constant.Code;
import com.example.twitterAnalog.domain.dto.WhoseComment;
import com.example.twitterAnalog.domain.dto.WhoseCommentRowMapper;
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

@Repository
@Transactional
@Slf4j
public class ReactionDaoImpl extends JdbcDaoSupport implements ReactionDao{

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void likePhrase(long userId, long phraseId) {
        jdbcTemplate.update("INSERT IGNORE INTO like_phrase(phrase_id, user_id) VALUES (?,?);", phraseId, userId);
    }

    @Override
    public void deleteLikePhrase(Long userId, long phraseId) {
        jdbcTemplate.update("DELETE FROM like_phrase WHERE phrase_id = ? AND user_id = ?;", phraseId, userId);
    }

    @Override
    public void commentPhrase(long userId, CommentPhraseReq req) {
        jdbcTemplate.update("INSERT IGNORE INTO comment(user_id, phrase_id, text) VALUES (?,?,?);",
                userId, req.getPhraseId(), req.getText());
    }

    @Override
    public WhoseComment whoseComment(long commentId) {
        try {
            return jdbcTemplate.queryForObject("SELECT comment.user_id AS comment_user_id, p.user_id AS phrase_user_id " +
                            "FROM comment JOIN phrase p on p.id = comment.phrase_id " +
                            "WHERE comment.id=?;"
            , new WhoseCommentRowMapper(), commentId);
        } catch (EmptyResultDataAccessException ex) {
            throw CommonException.builder().code(Code.COMMENT_NOT_FOUND).userMessage("Комментарий не найден")
                    .httpStatus(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public void deleteComment(long commentId) {
        jdbcTemplate.update("DELETE FROM comment WHERE comment_id = ?;", commentId);
    }
}
