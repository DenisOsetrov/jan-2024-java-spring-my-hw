package org.okten.osetrov.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

        // Використання анотацій Lombok для автоматичного генерування гетерів, сетерів,
        // конструктора без аргументів та конструктора з усіма аргументами
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматична генерація значення для первинного ключа

            private Long id; // Long - поверне null, а не 0!
            private String brand;
            private String model; // Модель автомобіля
            private int year;
            private int price;
            private double enginePower; // Потужність двигуна

            // Додамо поле torque пізніше
}
