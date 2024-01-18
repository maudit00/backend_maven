package it.epicode.week2.day3;

public class Customer {
    private long id;
    private String Name;
    private Integer tier;

    public Integer getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", tier=" + tier +
                '}';
    }

    public Customer(long id, String name, Integer tier) {
        this.id = id;
        Name = name;
        this.tier = tier;
    }

}
