package com.example.nexusserver.convert;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Convert
public class ListToStringConverter implements AttributeConverter<List<Integer>, String> {
    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        return attribute == null || attribute.isEmpty()
                ? ""
                : attribute.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        return dbData == null || dbData.isEmpty()
                ? List.of()
                : Arrays.stream(dbData.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
