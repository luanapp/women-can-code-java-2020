package br.com.wcc.banco.injector;

import br.com.wcc.banco.repositorio.ClienteRepositorio;
import br.com.wcc.banco.repositorio.ContaRepositorio;
import br.com.wcc.banco.repositorio.impl.ClienteRepositorioCSV;
import br.com.wcc.banco.repositorio.impl.ContaRepositorioCSV;

public class RepositoryInjector {

    public ClienteRepositorio getClienteRepositorio(Type type) {
        try {
            return getRepositorio(type, ClienteRepositorioCSV.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
    }

    public ContaRepositorio getContaRepositorio(Type type) {
        try {
            return getRepositorio(type, ContaRepositorioCSV.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        }
    }

    private <T> T getRepositorio(Type type, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        switch (type) {
            case CSV:
                return clazz.newInstance();
            case DATABASE:
            default:
                return null;
        }
    }

    public enum Type {
        CSV,
        DATABASE
    }
}
