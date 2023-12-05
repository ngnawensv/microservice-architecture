package com.belrose.writereposervice.constant.uri;

public @interface Uri {
    String DOCS= "/docs";
    String POST_DOCS="/docsInfo";
    String GET_DOCS="/list";

    String AGRE= "/agre";
    String URI_SAVE_AGRE= "/saveAgre";
    String URI_SAVE_AGRE_ENCODED= "/saveAgreEncoded";
    String FILE = "/file";
    String FILE_SAVE = "/saveFile";
    String LIST_AGRE_ENCODED = "/agreEncodedList";
    String LIST_AGRE ="/agreList" ;
    String GET_FILE = "/getFile";
}
