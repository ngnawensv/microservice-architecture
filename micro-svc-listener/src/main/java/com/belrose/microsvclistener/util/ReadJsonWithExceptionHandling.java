package com.belrose.microsvclistener.util;

import com.belrose.microsvclistener.exception.ReadJsonException;
import com.belrose.microsvclistener.exception.WriteJsonException;

@FunctionalInterface
public interface ReadJsonWithExceptionHandling<P,T,R,E extends Throwable> {
    R apply(P p, T t) throws  E;
    static <P, T,R,E extends  Throwable>  R  read(ReadJsonWithExceptionHandling<P, T,R,E> func, P p, T t)  {
        try {
            return func.apply(p,t);
        }catch (Throwable e){
            throw  new ReadJsonException(e);
        }
    }
}

