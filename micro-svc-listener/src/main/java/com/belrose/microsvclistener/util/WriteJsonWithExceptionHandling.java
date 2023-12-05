package com.belrose.microsvclistener.util;

import com.belrose.microsvclistener.exception.WriteJsonException;

@FunctionalInterface
public interface WriteJsonWithExceptionHandling<T,R,E extends Throwable> {
    R apply(T t) throws  E;

    static <T,R,E extends  Throwable>  R  write(WriteJsonWithExceptionHandling<T,R,E> func, T t)  {
         try {
             return func.apply(t);
         }catch (Throwable e){
             throw  new WriteJsonException(e);
         }
    }
}
