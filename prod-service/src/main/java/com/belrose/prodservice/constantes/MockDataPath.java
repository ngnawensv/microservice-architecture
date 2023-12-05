package com.belrose.prodservice.constantes;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum MockDataPath {

  PATH_TO_MOCK_PRODUCT_RESPONSE("mock-response/prod-mock-response.json");

  private final String name;
    MockDataPath(String name) {
        this.name = name;
    }
  private static final Map<MockDataPath,String> MOCK_DATA_PATH= Arrays.stream(MockDataPath.values()).collect(Collectors.toMap(mockDataPath -> mockDataPath,mockDataPath-> mockDataPath.name));

    public static String getName(MockDataPath mockDataPath){
        return MOCK_DATA_PATH.get(mockDataPath);
    }

}
