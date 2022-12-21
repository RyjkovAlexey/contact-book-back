package ru.alexey.contactbook.contactbookback.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ModelUtils {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static <T, S> T convertToDTO(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public static  <T, S> S convertToModel(T DTO, Class<S> targetClass) {
        return modelMapper.map(DTO, targetClass);
    }
}
