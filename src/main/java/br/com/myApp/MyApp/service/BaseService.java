package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BaseService<R extends CrudRepository, E, D> {

    private R repository;
    private final Constructor<?> constructor;

    public BaseService(R r) {
        var superclass = getClass().getGenericSuperclass();
        var type = ((ParameterizedType) superclass).getActualTypeArguments()[2];
        try {
            var clazz = Class.forName(type.getTypeName());
            this.constructor = clazz.getConstructors()[1];
        }catch (Exception e) {
            throw new BadRequestException("Ocorreu algum erro ao obter construtor");
        }

        this.setRepository(r);
    }

    public void setRepository(R repository) {
        this.repository = repository;
    }

    public R getRepository() {
        return this.repository;
    }

    @Transactional(rollbackFor = { Exception.class })
    public D save(E entity) {
        try {
            return (D) this.constructor.newInstance(this.repository.save(entity));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BadRequestException("Ocorreu um erro ao tentar salvar");
        }
    }

    public Optional<E> findById(Object id) {
        return this.repository.findById(id);
    }

    public List<D> findAll() {
        return (List<D>) StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(el -> constructorMethodDTO(el))
                .filter(el -> !Objects.isNull(el))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = { Exception.class })
    public void deleteById(Object id) {
        this.repository.deleteById(id);
    }

    @Transactional(rollbackFor = { Exception.class })
    public void delete(E e) {
        this.repository.delete(e);
    }

    @Transactional(rollbackFor = { Exception.class })
    public void deleteAll(List<E> list) {
        this.repository.deleteAll(list);
    }

    private Object constructorMethodDTO(Object entity) {
        try {
            return this.constructor.newInstance(entity);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }
}
