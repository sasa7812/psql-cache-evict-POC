package ru.savvy.entity.course;

import org.postgresql.util.PGobject;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.SQLException;

/**
 * Converter class for CourseMapped class, since postgres's JDBC driver maps all unknown (to Java)
 * types into {@link org.postgresql.util.PGobject} we make a converter from/into this type
 *
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@Converter
public class CourseMappedConverter implements AttributeConverter<String, PGobject> {
    @Override
    public PGobject convertToDatabaseColumn(String courseMapped) {
        System.err.println(" === convertToDatabaseColumn");
        try {
            PGobject po = new PGobject();
            po.setType("json"); // here we tell postgres to use actual psql's type "json"
            po.setValue(courseMapped);
            return po;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(PGobject po) {
        System.err.println(" === convertToEntityAttribute");

        return po.getValue();

    }
}
