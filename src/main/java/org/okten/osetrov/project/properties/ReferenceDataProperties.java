package org.okten.osetrov.project.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "reference-data")
public class ReferenceDataProperties {
    private List<FuelType> fuels;
}

// @ConfigurationProperties ідеально підходить для складних конфігураційних структур
// з вкладеними елементами або списками об’єктів.

// назва класу - ReferenceDataProperties - це - дані, що стосуються різних видів пального, тобто це "довідкові дані"
                 // Дані в класі є постійними або заздалегідь визначеними у вашій програмі.
                 // Цей клас відображає частину конфігураційного файлу application.yaml

// @ConfigurationProperties: Ця анотація вказує, що клас ReferenceDataProperties повинен отримувати значення з конфігураційного файлу, починаючи з префікса reference-data. Значення з цього префікса автоматично прив'язуються до відповідних полів класу.
//
//Поле fuels: Це поле отримує значення з конфігураційного файлу завдяки анотації @ConfigurationProperties. Оскільки YAML-файл містить секцію fuels, відповідно, це значення буде зчитано і зв'язано з полем fuels у класі ReferenceDataProperties.
//
//Генерація геттера: Lombok генерує геттер getFuels(), який повертає значення поля fuels.