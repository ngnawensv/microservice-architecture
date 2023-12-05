package com.belrose.agreservice.util;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ModelToXmlGeneratorTest {

    private final TestHelper testHelper = new TestHelper();

    @Test
    public void modelToXmlTest() throws IOException {
        //assertThat(ModelToXmlGenerator.modelToXml(testHelper.getAgre())).contains("success");

        try(MockedStatic<ModelToXmlGenerator> modelToXmlGeneratorMockedStatic = Mockito.mockStatic(ModelToXmlGenerator.class)){
            modelToXmlGeneratorMockedStatic.when(()->ModelToXmlGenerator.modelToXml(testHelper.getAgre())).thenReturn("success");
              //  assertThat(ModelToXmlGenerator.modelToXml(testHelper.getAgre())).contains("success");
        }
        assertThat(ModelToXmlGenerator.modelToXml(testHelper.getAgre())).contains(testHelper.getAgre().getName());
    }

}
