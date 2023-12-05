package com.belrose.docservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Translator {
    private  static ResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ResourceBundleMessageSource messageSource){
        Translator.messageSource=messageSource;
    }
    public  static String toLocal(String msg){
        var local = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msg,null,local);
    }
}
