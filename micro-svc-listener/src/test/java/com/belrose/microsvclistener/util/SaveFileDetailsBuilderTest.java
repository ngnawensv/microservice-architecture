package com.belrose.microsvclistener.util;


import com.belrose.microsvclistener.util.file.SaveFileDetailsBuilder;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

// TODO  Make it  to work
import static org.assertj.core.api.Assertions.assertThat;

    @ExtendWith(MockitoExtension.class)
    public class SaveFileDetailsBuilderTest {

        private final TestHelper testHelper = new TestHelper();

        @Test
        public void buildSaveFileDetailsTest() throws IOException {

            try(MockedStatic<SaveFileDetailsBuilder> saveFileDetailsBuilderMockedStatic = Mockito.mockStatic(SaveFileDetailsBuilder.class)){
                saveFileDetailsBuilderMockedStatic.when(()->SaveFileDetailsBuilder.buildSaveFileDetails(testHelper.getAgre())).thenReturn(testHelper.getSaveFileDetails());
            }
            assertThat(SaveFileDetailsBuilder.buildSaveFileDetails(testHelper.getAgre()));
        }

    }

