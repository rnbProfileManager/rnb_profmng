package com.rnb.profmng.entity.project;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Timestamp.valueOf(locDate.atStartOfDay()));
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime().toLocalDate());
    }
}
