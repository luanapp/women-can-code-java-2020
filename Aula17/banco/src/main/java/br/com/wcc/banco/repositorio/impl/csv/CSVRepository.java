package br.com.wcc.banco.repositorio.impl.csv;

import br.com.wcc.banco.file.ResourceFile;
import br.com.wcc.banco.repositorio.impl.csv.enums.CSVHeaders;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class CSVRepository {
    <H extends Enum & CSVHeaders, E> List<E> getAll(String filename, Class<H> enumClass, Class<E> entityClass) {
        try {
            final ResourceFile resourceFile = new ResourceFile();
            final FileReader file = resourceFile.getFileReaderFromResource(filename);
            final List<E> all = new ArrayList<>();

            final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader((Class<? extends Enum<?>>) enumClass)
                    .withFirstRecordAsHeader()
                    .parse(file);
            for (final CSVRecord csvRecord : records) {
                final Class[] constructorClasses = Arrays.stream(enumClass.getEnumConstants())
                        .map(H::getEntityClass)
                        .toArray(Class[]::new);
                final Object[] constructorValues = Arrays.stream(enumClass.getEnumConstants())
                        .map(header -> getValue(csvRecord, header))
                        .toArray(Object[]::new);

                all.add(entityClass.getConstructor(constructorClasses).newInstance(constructorValues));

            }

            return all;
        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            return Collections.emptyList();
        }
    }

    <I, H extends Enum & CSVHeaders, E> E getById(I id, String filename, Class<H> enumClass, Class<E> entityClass) {
        try {
            final ResourceFile resourceFile = new ResourceFile();
            final FileReader file = resourceFile.getFileReaderFromResource(filename);

            final Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader((Class<? extends Enum<?>>) enumClass)
                    .withFirstRecordAsHeader()
                    .parse(file);
            for (final CSVRecord csvRecord : records) {
                final Integer entityId = Integer.parseInt(csvRecord.get(CSVHeaders.getFirst(enumClass)));
                if (id.equals(entityId)) {
                    final Class[] constructorClasses = Arrays.stream(enumClass.getEnumConstants())
                            .map(H::getEntityClass)
                            .toArray(Class[]::new);
                    final Object[] constructorValues = Arrays.stream(enumClass.getEnumConstants())
                            .map(header -> getValue(csvRecord, header))
                            .toArray(Object[]::new);


                    return entityClass.getConstructor(constructorClasses).newInstance(constructorValues);
                }
            }

            return null;
        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            return null;
        }
    }

    private <H extends Enum & CSVHeaders> Object getValue(CSVRecord csvRecord, H header) {
        try {
            if (header.getEntityClass().isEnum()) {
                return Enum.valueOf(header.getEntityClass(), csvRecord.get(header));
            }
            return header.getEntityClass().getConstructor(String.class).newInstance(csvRecord.get(header));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
