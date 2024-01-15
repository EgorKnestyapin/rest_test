package de.aittr.g_31_2_rest.domain;

import java.util.Objects;

public class Dog {
    private int id;
    private String name;
    private String color;
    private double weight;

    public Dog() {
    }

    public Dog(String name, String color, double weight) {
        this.name = name;
        this.color = color;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return id == dog.id && Double.compare(weight, dog.weight) == 0 && Objects.equals(name, dog.name) && Objects.equals(color, dog.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, weight);
    }

    @Override
    public String toString() {
        return String.format("Собака: ИД - %d, кличка - %s, цвет - %s, вес - %.2f", id, name, color, weight);
    }
}
