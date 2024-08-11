package org.okten.osetrov.project.repository;

import org.okten.osetrov.project.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Позначає клас як репозиторій для роботи з базою даних

public interface CarRepository extends JpaRepository<Car, Long> {

    // створення методу за власними параметрами - https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    // описи власних методів за їх назвою можна переглянути в таблиці
    // шукає по назві поля в Entity, а не по назві колонки!!!

    // Метод для пошуку автомобілів за параметрами потужності двигуна
    List<Car> findByEnginePowerBetween(double minEnginePower, double maxEnginePower);


    // приклади інших методів:

//    1. Пошук автомобілів за мінімальним і максимальним роком випуску
//            Опис: Цей запит знайде всі автомобілі, випущені в заданий період.
//    List<Car> findByYearBetween(int minYear, int maxYear);

//    2. Пошук автомобілів за мінімальною і максимальною ціною
//            Опис: Цей запит знайде всі автомобілі, які мають ціну в межах заданого діапазону.
//    List<Car> findByPriceBetween(int minPrice, int maxPrice);

//    3. Пошук автомобілів за брендом
//            Опис: Цей запит знайде всі автомобілі, що належать до певного бренду.
//    List<Car> findByBrand(String brand);

//    4. Пошук автомобілів за моделлю
//            Опис: Цей запит знайде всі автомобілі певної моделі.
//    List<Car> findByModel(String model);
//
//    5. Пошук автомобілів за поєднанням року і потужності двигуна
//            Опис: Цей запит знайде всі автомобілі певного року, які мають потужність двигуна в межах заданого діапазону.
//    List<Car> findByYearAndEnginePowerBetween(int year, double minEnginePower, double maxEnginePower);
//
//
//    Використання в контролері
//    Для кожного з цих запитів можна створити відповідний метод у контролері, щоб обробляти HTTP-запити.
//
//    Наприклад:
//    @GetMapping("/search-by-year")
//    public List<Car> searchCarsByYear(@RequestParam int minYear, @RequestParam int maxYear) {
//        return carRepository.findByYearBetween(minYear, maxYear);
//    }
}

//  Метод findByEnginePowerBetween у вашому випадку використовується для пошуку автомобілів,
//  які мають потужність двигуна в межах заданого діапазону. Цей метод створений автоматично
//  завдяки Spring Data JPA на основі назви методу, без потреби писати SQL-запит вручну.

        // Назва методу:
                //  - findBy означає, що метод виконує пошук.
                //  - EnginePower визначає поле, по якому виконується пошук.
                //  - Between вказує на те, що метод повинен знайти всі записи, де значення EnginePower знаходиться між двома параметрами (включно).