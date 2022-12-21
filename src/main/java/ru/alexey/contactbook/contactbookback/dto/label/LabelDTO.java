package ru.alexey.contactbook.contactbookback.dto.label;

import java.util.Objects;

public class LabelDTO {
    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabelDTO labelDTO = (LabelDTO) o;

        if (id != labelDTO.id) return false;
        return Objects.equals(name, labelDTO.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LabelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
