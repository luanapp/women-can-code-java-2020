package br.com.wcc.banco.repositorio.impl.csv.enums;

public interface CSVHeaders<T extends Enum> {
    T getId();
    Class<?> getEntityClass();

    static <E extends Enum> E getFirst(Class<E> enumClass) {
        return enumClass.getEnumConstants()[0];
    }
}
