package org.okten.osetrov.project.controller;

import lombok.RequiredArgsConstructor;
import org.okten.osetrov.project.properties.FuelType;
import org.okten.osetrov.project.properties.ReferenceDataProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor // анотація будує конструктор ін'єкцій залежностей
public class ReferenceDataController {

    // Ін'єкція значень з application.yaml за допомогою анотації @Value
    @Value("${reference-data.engine-types}")
    private List<String> engineTypes;

    // Ін'єкція залежностей через конструктор
    private final ReferenceDataProperties referenceDataProperties;


    // Обробка GET-запиту на /api/engine-types
    @GetMapping("/engine-types")
    public ResponseEntity<List<String>> getEngineTypes() {
        // Повернення списку типів двигунів
        return ResponseEntity.ok(engineTypes);
    }

    // Обробка GET-запиту на /api/fuel-types
    @GetMapping("/fuel-types")
    public ResponseEntity<List<FuelType>> getFuelTypes() {
        // Повернення списку типів палива з варіантами
        return ResponseEntity.ok(referenceDataProperties.getFuels());
    }

    // Обробка GET-запиту на /api/fuel-types/{fuelName}
    @GetMapping("/fuel-types/{fuelName}")
    public ResponseEntity<FuelType> getFuelTypeByName(@PathVariable String fuelName) {
        return referenceDataProperties.getFuels().stream()
                .filter(fuelType -> Objects.equals(fuelType.getName(), fuelName))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Обробка GET-запиту на /api/all-reference-data
    @GetMapping("/all-reference-data")
    public ResponseEntity<ReferenceDataProperties> getAllReferenceData() {
        // Повернення всіх даних з reference-data
        return ResponseEntity.ok(referenceDataProperties);
    }

}