package ru.savvy.entity.converter;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */
import java.sql.Timestamp;
import org.joda.time.DateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JodaDateTimeConverter implements AttributeConverter<DateTime,Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(DateTime dateTime) {
        return dateTime == null ? null : new Timestamp((dateTime).getMillis());
    }

    @Override
    public DateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp == null ? null : new DateTime(timestamp);
    }
}