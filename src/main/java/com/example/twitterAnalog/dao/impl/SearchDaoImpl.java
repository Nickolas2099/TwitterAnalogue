package com.example.twitterAnalog.dao.impl;

import com.example.twitterAnalog.dao.SearchDao;
import com.example.twitterAnalog.domain.api.common.PhraseRespRowMapper;
import com.example.twitterAnalog.domain.api.common.TagResp;
import com.example.twitterAnalog.domain.api.common.TagRespRowMapper;
import com.example.twitterAnalog.domain.api.search.SearchPhrasesByTagReq;
import com.example.twitterAnalog.domain.api.common.PhraseResp;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
@Transactional
public class SearchDaoImpl extends JdbcDaoSupport implements SearchDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<PhraseResp> searchPhrasesByTag(SearchPhrasesByTagReq req) {
        return jdbcTemplate.query("SELECT phrase.id AS phrase_id, u.id AS user_id, u.nickname, phrase.text, phrase.time_insert " +
                "FROM phrase " +
                "   JOIN user u on phrase.user_id = u.id " +
                "WHERE phrase.id IN (SELECT phrase_id FROM phrase_tag WHERE tag_id = ?) " +
                "ORDER BY " + req.getSort().getValue() + ";", new PhraseRespRowMapper(), req.getTagId());
    }

    @Override
    public List<TagResp> searchTags(String partTag) {
        return jdbcTemplate.query("SELECT id, text " +
                "FROM (" +
                "   SELECT tag.id, text, COUNT(tag.id) AS c " +
                "   FROM tag " +
                "       JOIN phrase_tag pt ON tag.id = pt.tag_id " +
                "   WHERE text LIKE CONCAT(LOWER(?), '%') " +
                "   GROUP BY tag:id " +
                "   ORDER BY count(tag.id) DESC) t1 " +
                "UNION " +
                "SELECT id, text " +
                "FROM (" +
                "   SELECT tag.id, text, count(tag.id) AS c " +
                "   FROM tag " +
                "       JOIN phrase_tag pt ON tag.id = pt.tag_id " +
                "   WHERE text LIKE CONCAT('%_', LOWER(?), '%') " +
                "   GROUP BY tag.id " +
                "   ORDER BY COUNT(tag.id) DESC) t2;", new TagRespRowMapper(), partTag, partTag);
    }//CONCAT() - соединяет значения в одну строку


}
