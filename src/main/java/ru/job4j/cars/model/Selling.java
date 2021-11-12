package ru.job4j.cars.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Класс Selling - описывает модель данных объявления о продаже.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "selling")
public class Selling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String header;
    private String description;
    private double price;
    private Timestamp created;
    private boolean sold;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "car_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Car car;

    public Selling() {
    }

    public static Selling of(String header, String description,
                             double price, Timestamp created,
                             boolean sold, User user, Car car) {
        Selling selling = new Selling();
        selling.header = header;
        selling.description = description;
        selling.price = price;
        selling.created = created;
        selling.sold = sold;
        selling.user = user;
        selling.car = car;
        return selling;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Selling selling = (Selling) o;
        return id == selling.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Selling{"
                + "id=" + id
                + ", header='" + header + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + ", created=" + created
                + ", sold=" + sold
                + ", user=" + user
                + ", car=" + car
                + '}';
    }
}
