package ru.job4j.cars.model;

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
    private String description;
    private String model;
    private String body;
    private boolean sold;
    private Timestamp created;
    private boolean photo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Selling() {
    }

    public Selling(String description, String model, String body,
                   boolean sold, User user) {
        this.description = description;
        this.model = model;
        this.body = body;
        this.sold = sold;
        this.user = user;
    }

    public Selling(String description, String model, String body,
                   boolean sold, Timestamp created, boolean photo, User user) {
        this.description = description;
        this.model = model;
        this.body = body;
        this.sold = sold;
        this.created = created;
        this.photo = photo;
        this.user = user;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
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
                + ", description='" + description + '\''
                + ", model='" + model + '\''
                + ", body='" + body + '\''
                + ", sold=" + sold
                + ", created=" + created
                + ", photo=" + photo
                + ", user=" + user
                + '}';
    }
}
