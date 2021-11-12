package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс Car - описывает модель данных автомобиля.
 * Создание объекта при помощи шаблона "Builder":
 *          Car car = new Car.Builder()
 *                 .buildId(1)
 *                 .buildBrand("Volvo")
 *                 .buildModel("XC-50")
 *                 .buildYear(2021)
 *                 .buildTransmission("Автомат")
 *                 .buildBody("Седан")
 *                 .buildEngine("Бензин")
 *                 .buildMileage(25_000)
 *                 .buildEngineCapacity(2.5)
 *                 .buildWheel("Передний")
 *                 .buildColor("Черный")
 *                 .build();
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;

    private String model;

    private int year;

    private String transmission;

    private String body;

    private String engine;

    private int mileage;

    private double engineCapacity;

    private String wheel;

    private String color;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", year=" + year
                + ", transmission='" + transmission + '\''
                + ", body='" + body + '\''
                + ", engine='" + engine + '\''
                + ", mileage=" + mileage
                + ", engineCapacity=" + engineCapacity
                + ", wheel='" + wheel + '\''
                + ", color='" + color + '\''
                + '}';
    }


    public static class Builder {
        private int id;
        private String brand;
        private String model;
        private int year;
        private String transmission;
        private String body;
        private String engine;
        private int mileage;
        private double engineCapacity;
        private String wheel;
        private String color;

        public Builder buildId(int id) {
            this.id = id;
            return this;
        }

        public Builder buildBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder buildModel(String model) {
            this.model = model;
            return this;
        }

        public Builder buildYear(int year) {
            this.year = year;
            return this;
        }

        public Builder buildTransmission(String transmission) {
            this.transmission = transmission;
            return this;
        }

        public Builder buildBody(String body) {
            this.body = body;
            return this;
        }

        public Builder buildEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public Builder buildMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Builder buildEngineCapacity(double engineCapacity) {
            this.engineCapacity = engineCapacity;
            return this;
        }

        public Builder buildWheel(String wheel) {
            this.wheel = wheel;
            return this;
        }

        public Builder buildColor(String color) {
            this.color = color;
            return this;
        }

        public Car build() {
            Car car = new Car();
            car.id = id;
            car.brand = brand;
            car.model = model;
            car.year = year;
            car.transmission = transmission;
            car.body = body;
            car.engine = engine;
            car.mileage = mileage;
            car.engineCapacity = engineCapacity;
            car.wheel = wheel;
            car.color = color;
            return car;
        }
    }

}
