package hackday.speechAccent.dao;

import hackday.speechAccent.dto.Rate;
import hackday.speechAccent.model.Language;
import hackday.speechAccent.model.Languages;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by nicaraguanec on 16.04.2016.
 */
@Repository
public class LanguageDao extends AbstractDao {
    private static final String SELECT_ACCENT_WITH_LINK = "SELECT language.iso_name, record.link FROM (SELECT language.id FROM public.language WHERE iso_name = ?) a_id, public.record, public.text, public.language WHERE text.lang_id = a_id.id AND record.text_id = texT.id AND record.accent_id = language.id;";
    private static final String SELECT_ORIGINAL_TEXT = "SELECT DISTINCT language.iso_name original, text.text FROM public.record, public.language, public.text, (SELECT language.iso_name FROM public.language, public.record WHERE record.accent_id = language.id) AS accent WHERE text.lang_id = language.id ORDER BY original;";
    private static final String INSERT_TEXT = "INSERT INTO public.text (lang_id, text) VALUES ((SELECT language.id FROM public.language WHERE language.iso_name = ?),?)";
    private static final String INSERT_RECORD = "INSERT INTO public.record (text_id, accent_id, date, link) VALUES (?,(SELECT language.id FROM public.language WHERE language.iso_name = ?),now(),?)";
    private static final String INSERT_RATE = "INSERT INTO public.rate (record_id, rate) VALUES ((SELECT record.id FROM public.record WHERE link = ?), ?)";
    private static final String SELECT_DISTINCT_RATE_BY_RECORD_NAME = "SELECT DISTINCT rate FROM public.rate, public.record WHERE rate.record_id = record.id AND record.link = ?";
    private static final String SELECT_RATE_BY_RECORD_NAME = "SELECT avg(rate) FROM (SELECT rate FROM public.rate, public.record WHERE rate.record_id = record.id AND record.link = ?) AS rates;";


    public Languages getLanguagesWithText() {
        List<Triple<String, String, String>> list = getJdbcTemplate().query(SELECT_ORIGINAL_TEXT, new RowMapper<Triple<String, String, String>>() {
            public Triple<String, String, String> mapRow(ResultSet resultSet, int i) throws SQLException {
                return new ImmutableTriple<>(
                        resultSet.getString("original"),
                        null,
                        resultSet.getString("text")
                );
            }
        });
        List<Language> langList = new ArrayList<>(list.size());

        for (Triple<String, String, String> triple : list) {
            String original = triple.getLeft();
            String text = triple.getRight();
            Language language = new Language(original, text);
            language.setAccents(getAvailableAccentToLang(original));
            langList.add(language);
        }
        return new Languages(langList);
    }

    private Map<String, String> getAvailableAccentToLang(String isoLang) {
        return getJdbcTemplate().query(SELECT_ACCENT_WITH_LINK, new ResultSetExtractor<Map<String, String>>() {
            @Override
            public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                HashMap<String, String> result = new HashMap<>();
                while (rs.next()) {
                    result.put(rs.getString("iso_name"), rs.getString("link"));
                }
                return result;
            }
        }, isoLang);
    }

    public boolean createRecord(String origin, String accent, String text, String filename) {
        return getJdbcTemplate().update(INSERT_RECORD, createText(origin, text), accent, filename) > 0;
    }

    private int createText(final String origin, final String text) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_TEXT, new String[]{"id"});
                preparedStatement.setString(1, origin);
                preparedStatement.setString(2, text);
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public boolean rateRecord(String link, int rate) {
        return getJdbcTemplate().update(INSERT_RATE, link, rate) > 0;
    }

    public Rate getRate(final String recordName) {
        Rate rate = getJdbcTemplate().queryForObject(SELECT_DISTINCT_RATE_BY_RECORD_NAME, new RowMapper<Rate>() {
            @Override
            public Rate mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Rate(recordName, rs.getInt("rate"), rs.getInt("rate"));
            }
        }, recordName);
        rate.setAverageRate(getJdbcTemplate().queryForObject(SELECT_RATE_BY_RECORD_NAME, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("avg");
            }
        }, recordName));
        return rate;
    }
}
