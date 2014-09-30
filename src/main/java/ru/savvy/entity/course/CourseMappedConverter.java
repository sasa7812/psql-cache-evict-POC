package ru.savvy.entity.course;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.postgresql.util.PGobject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Converter class for CourseMapped class, since postgres's JDBC driver maps all unknown (to Java)
 * types into {@link org.postgresql.util.PGobject} we make a converter from/into this type
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@Converter
public class CourseMappedConverter implements AttributeConverter<CourseMapped, PGobject> {
    @Override
    public PGobject convertToDatabaseColumn(CourseMapped courseMapped) {
        try {
            PGobject po = new PGobject();
            po.setType("json"); // here we tell postgres to use actual psql's type "json"
            po.setValue((new ObjectMapper()).writeValueAsString(courseMapped));
            return po;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CourseMapped convertToEntityAttribute(PGobject po) {
        try {
            return (new ObjectMapper()).readValue(po.getValue(),CourseMapped.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
