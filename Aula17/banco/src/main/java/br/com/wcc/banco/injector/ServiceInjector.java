package br.com.wcc.banco.injector;

import br.com.wcc.banco.repositorio.Repositorio;
import br.com.wcc.banco.servico.Servico;

import java.lang.reflect.InvocationTargetException;

public class ServiceInjector {
    public <T extends Servico, R extends Repositorio> T getServico(Class<T> clazz, R repositorio) {
        try {
            return clazz.getDeclaredConstructor(repositorio.getClass().getInterfaces()[0]).newInstance(repositorio);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
