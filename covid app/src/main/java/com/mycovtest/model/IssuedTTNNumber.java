package com.mycovtest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class IssuedTTNNumber {
    private @Id
    @GeneratedValue
    Long id;
    private String ttnNumber;

    public IssuedTTNNumber() {
    }

    public IssuedTTNNumber(String ttnNumber) {
        this.ttnNumber = ttnNumber;
    }

    public String getTtnNumber() {
        return ttnNumber;
    }

    public void setTtnNumber(String ttnNumber) {
        this.ttnNumber = ttnNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssuedTTNNumber that = (IssuedTTNNumber) o;
        return Objects.equals(ttnNumber, that.ttnNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ttnNumber);
    }
}
