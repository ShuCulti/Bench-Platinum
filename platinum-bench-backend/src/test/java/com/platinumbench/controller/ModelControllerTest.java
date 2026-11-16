package com.platinumbench.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.platinumbench.domain.Model;
import com.platinumbench.logic.ModelService;
import com.platinumbench.logic.exception.DuplicateModelException;

@ExtendWith(MockitoExtension.class)
class ModelControllerTest {

    @Mock
    private ModelService modelService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ModelController(modelService))
                .setControllerAdvice(new GEH())
                .build();
    }

    @Test
    void createModel_validRequest_returns201WithCreatedModel() throws Exception {
        Model saved = new Model(5L, "Phi-4", "Phi", new BigDecimal("14.7"));
        when(modelService.createModel(any(Model.class))).thenReturn(saved);

        mockMvc.perform(post("/api/models")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                { "name": "Phi-4", "family": "Phi", "parameterB": 14.7 }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Phi-4"))
                .andExpect(jsonPath("$.family").value("Phi"));
    }

    @Test
    void createModel_invalidFields_returns400WithFieldErrors() throws Exception {
        mockMvc.perform(post("/api/models")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                { "name": "", "family": "", "parameterB": -5 }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("Validation failed"))
                .andExpect(jsonPath("$.errors.name").exists())
                .andExpect(jsonPath("$.errors.family").exists())
                .andExpect(jsonPath("$.errors.parameterB").exists());

        verifyNoInteractions(modelService);
    }

    @Test
    void createModel_duplicateName_returns409() throws Exception {
        when(modelService.createModel(any(Model.class))).thenThrow(new DuplicateModelException("Phi-4"));

        mockMvc.perform(post("/api/models")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                { "name": "Phi-4", "family": "Phi", "parameterB": 14.7 }
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.detail").value("Model named Phi-4 already exists"));
    }

    @Test
    void getModelById_existingId_returns200WithModel() throws Exception {
        Model model = new Model(1L, "Qwen2.5-7B", "Qwen", new BigDecimal("7.62"));
        when(modelService.getModelById(1L)).thenReturn(Optional.of(model));

        mockMvc.perform(get("/api/models/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Qwen2.5-7B"));
    }

    @Test
    void getModelById_unknownId_returns404() throws Exception {
        when(modelService.getModelById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/models/99"))
                .andExpect(status().isNotFound());
    }
}
