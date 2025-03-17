package com.mitocode.service.impl;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.repository.IGenericRepository;
import com.mitocode.service.ICRUD;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public abstract class ICRUDImpl <T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepository<T, ID> getRepository();

    @Override
    public List<T> findAll() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public T save(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public T update(ID id, T t) throws Exception {
        //t.set Java Reflection
        Class<?> clazz = t.getClass();
        String className = clazz.getSimpleName();

        //SetXYZ
        String methodName = "setId" + className;
        Method setIdMethod = clazz.getMethod(methodName, id.getClass());
        setIdMethod.invoke(t, id);

        getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        return getRepository().save(t);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepository().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        getRepository().deleteById(id);
    }
}
