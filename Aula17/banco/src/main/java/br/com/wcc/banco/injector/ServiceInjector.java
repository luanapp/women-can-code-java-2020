package br.com.wcc.banco.injector;

import br.com.wcc.banco.servico.Servico;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ServiceInjector {
    public <T extends Servico> T getServico(Class<T> clazz, Object... parametros) {
        try {
            Class[] classes = new Class[parametros.length];
            Arrays.stream(parametros)
                    .map(Object::getClass)
                    .collect(Collectors.toList())
                    .toArray(classes);
            return clazz.getDeclaredConstructor(classes).newInstance(parametros);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
