package com.lank.AnimalShop.object;

public class Cart {
    public Cart(int id, String name, int price, byte[] img, int number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int id;
    private String name;
    private int price;
    private byte[] img;
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
