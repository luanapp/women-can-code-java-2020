package br.com.wcc.banco.injector;

import br.com.wcc.banco.repositorio.Repositorio;

import java.lang.reflect.InvocationTargetException;

public class RepositoryInjector {


    public <T extends Repositorio> T getRepositorio(Type type, Class<T> clazz) {
        try {
            switch (type) {
                case CSV:
                    return clazz.getDeclaredConstructor().newInstance();
                case DATABASE:
                default:
                    return null;
            }
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            return null;
        }
    }

    public enum Type {
        CSV,
        DATABASE
    }
}
