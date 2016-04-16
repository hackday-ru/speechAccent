package hackday.speechAccent.dao;

import hackday.speechAccent.model.Language;
import hackday.speechAccent.model.Languages;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
                    result.put(rs.getString("iso_name"),rs.getString("link"));
                }
                return result;
            }
        }, isoLang);
    }
}
