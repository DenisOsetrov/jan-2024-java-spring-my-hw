package org.okten.osetrov.project.controller;


import lombok.RequiredArgsConstructor;
import org.okten.osetrov.project.entity.Car;
import org.okten.osetrov.project.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Контролер для обробки HTTP-запитів, пов'язаних з автомобілями

        // Аннотація, що вказує, що клас є REST-контролером в Spring
        // і всі його методи будуть обробляти HTTP-запити
@RestController

        // Вказує базовий URL для всіх запитів у цьому контролері
@RequestMapping("/cars")

        // Аннотація Lombok, що генерує конструктор для всіх final-полів
        // Дозволяє нам уникнути використання @Autowired
@RequiredArgsConstructor

public class CarController {

    // Репозиторій для доступу до даних про автомобілі в базі даних
    private final CarRepository carRepository;
                        // або
                        // @Autowired // Впровадження залежності для CarRepository (для ознайомлення)
                        // private CarRepository carRepository;

    // Метод для отримання списку всіх автомобілів без фільтрів
    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Він дозволяє шукати автомобілі по діапазону потужності двигуна,
    // з параметрами minEnginePower і maxEnginePower. За замовчуванням, якщо параметри не задані,
    // він шукає автомобілі з потужністю від 0 до 10000.
    // Метод для пошуку автомобілів за потужністю двигуна
    @GetMapping("/search")
    public List<Car> searchCars(
            @RequestParam(required = false, defaultValue = "0") double minEnginePower,
            @RequestParam(required = false, defaultValue = "10000") double maxEnginePower) {
        return carRepository.findByEnginePowerBetween(minEnginePower, maxEnginePower);
    }

             // Метод для отримання списку всіх автомобілів
             // що відповідають певним параметрам потужності двигуна.
             // Якщо в запиті присутні параметри minEnginePower і maxEnginePower,
             // він повертає список автомобілів, де потужність двигуна знаходиться між цими двома значеннями.
             // Якщо параметри не задані, повертає всі автомобілі.

                     // @GetMapping обробляє HTTP GET-запити до "/cars/default"
                       @GetMapping("/default")
                       public List<Car> getAllCars2(@RequestParam(required = false) Integer minEnginePower,
                                                   @RequestParam(required = false) Integer maxEnginePower) {
                           // Перевіряємо, чи обидва параметри запиту присутні
                           if (minEnginePower != null && maxEnginePower != null) {
                               // Повертаємо список автомобілів, що відповідають умовам потужності двигуна
                               return carRepository.findByEnginePowerBetween(minEnginePower, maxEnginePower);
                           }
                           // Якщо параметри не задані, повертаємо всі автомобілі
                           return carRepository.findAll();
                       }

    // Метод для отримання конкретного автомобіля за ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .map(ResponseEntity::ok) // Якщо автомобіль знайдено, повертаємо його
                .orElseGet(() -> ResponseEntity.notFound().build()); // Якщо ні, повертаємо 404
    }


    // Метод для створення нового автомобіля
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car savedCar = carRepository.save(car); // Зберігаємо новий автомобіль у базі даних
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    // Метод для оновлення даних про автомобіль за ID
    // Обробляє HTTP PUT-запити до "/cars/{id}"
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        // Шукаємо автомобіль за ID
        return carRepository.findById(id)
                .map(car -> {
                    // Оновлюємо дані автомобіля
                    car.setBrand(updatedCar.getBrand());
                    car.setModel(updatedCar.getModel());
                    car.setYear(updatedCar.getYear());
                    car.setEnginePower(updatedCar.getEnginePower());
//                    car.setTorque(updatedCar.getTorque()); // Оновлення torque
                    // Зберігаємо оновлений автомобіль і повертаємо його з HTTP статусом 200 OK
                    return ResponseEntity.ok(carRepository.save(car));
                })
                // Якщо автомобіль не знайдено, повертаємо HTTP статус 404 Not Found
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

            // Метод для видалення автомобіля за ID
            // Обробляє HTTP DELETE-запити до "/cars/{id}"
            @DeleteMapping("/{id}")
            public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
                // Перевіряємо, чи існує автомобіль з вказаним ID
                if (carRepository.existsById(id)) {
                    // Видаляємо автомобіль з бази даних
                    carRepository.deleteById(id);
                    // Повертаємо HTTP статус 204 No Content
                    return ResponseEntity.noContent().build();
                } else {
                    // Якщо автомобіль не знайдено, повертаємо HTTP статус 404 Not Found
                    return ResponseEntity.notFound().build();
                }
            }

}
