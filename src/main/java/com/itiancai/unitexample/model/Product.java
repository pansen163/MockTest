package com.itiancai.unitexample.model;

/**
 * Created by pansen on 15/8/15.
 */
public class Product {

  public String weight;
  public String hight;

  public Product(String weight, String hight) {
    this.weight = weight;
    this.hight = hight;
  }

  public String getWeight() {
    return weight;

  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getHight() {
    return hight;
  }

  public void setHight(String hight) {
    this.hight = hight;
  }
}
