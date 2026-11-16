package com.platinumbench.logic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.platinumbench.domain.Model;
import com.platinumbench.logic.exception.DuplicateModelException;
import com.platinumbench.persistence.ModelRepository;

@ExtendWith(MockitoExtension.class)
class ModelServiceTest {

    @Mock
    private ModelRepository modelRepository;

    @InjectMocks
    private ModelService modelService;

    @Test
    void createModel_newName_savesAndReturnsModel() {
        Model newModel = new Model(null, "Phi-4", "Phi", new BigDecimal("14.7"));
        Model saved = new Model(5L, "Phi-4", "Phi", new BigDecimal("14.7"));
        when(modelRepository.getByName("Phi-4")).thenReturn(Optional.empty());
        when(modelRepository.create(newModel)).thenReturn(saved);

        Model result = modelService.createModel(newModel);

        assertThat(result.getId()).isEqualTo(5L);
        assertThat(result.getName()).isEqualTo("Phi-4");
        verify(modelRepository).create(newModel);
    }

    @Test
    void createModel_duplicateName_throwsAndDoesNotSave() {
        Model existing = new Model(5L, "Phi-4", "Phi", new BigDecimal("14.7"));
        Model newModel = new Model(null, "Phi-4", "Phi", new BigDecimal("14.7"));
        when(modelRepository.getByName("Phi-4")).thenReturn(Optional.of(existing));

        DuplicateModelException ex = assertThrows(DuplicateModelException.class,
                () -> modelService.createModel(newModel));

        assertThat(ex.getMessage()).isEqualTo("Model named Phi-4 already exists");
        verify(modelRepository, never()).create(any());
    }

    @Test
    void getModelById_unknownId_returnsEmpty() {
        when(modelRepository.getById(99L)).thenReturn(Optional.empty());

        Optional<Model> result = modelService.getModelById(99L);

        assertThat(result).isEmpty();
    }
}
