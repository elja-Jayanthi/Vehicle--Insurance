package com.auto.insurance;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by uengine on 2018. 10. 25..
 */
@Entity
public class Vehicle {

    @Id
    String id;
    String modelName;

    @ManyToOne
    @JoinColumn(name="owner_id")
    Customer owner;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }


}
