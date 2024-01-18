package week2.day4;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Product p1 = new Product(1, "Harry Potter", "Books", 200.3);
        Product p2= new Product(2, "Il Signore degli anelli ", "Books", 200.10);
        Product p3 = new Product(3, "Tre Metri sopra il cielo", "Books", 10);
        Product p4 = new Product(4, "Paperella di gomma", "Baby", 10);
        Product p5 = new Product(5, "Coltellino Svizzero", "Boys", 30);
        Product p6 = new Product(6, "Bavaglino", "Baby", 8);
        Product p7 = new Product(7, " Maglia Squadra del cuore", "Boys", 80);

        Customer c1 = new Customer(1, "Mario Rossi", 2);
        Customer c2 = new Customer(2, "Bruno Giordano", 1);
        Customer c3 = new Customer(3, "Antonio Verdi", 2);
        Customer c4 = new Customer(4, "Maria Cicala", 3);

        Order o1 = new Order(1, "delivered", LocalDate.parse("2021-02-02"), LocalDate.parse("2021-02-05"), List.of(p1,p2,p3), c1);
        Order o2 = new Order(2, "delivered", LocalDate.parse("2021-02-03"), LocalDate.parse("2021-02-05"), List.of(p1,p5,p4), c3);
        Order o3 = new Order(3, "delivered", LocalDate.parse("2023-05-07"), LocalDate.parse("2023-05-17"), List.of(p2,p3,p6), c2);
        Order o4 = new Order(4, "delivered", LocalDate.parse("2023-06-07"), LocalDate.parse("2023-06-17"), List.of(p2,p3,p6), c2);
        Order o5 = new Order(5, "delivered", LocalDate.parse("2021-02-03"), LocalDate.parse("2021-02-05"), List.of(p1,p5,p7), c4);

        List<Order> orders = List.of(o1,o2,o3,o4,o5);
        List<Product> products = List.of(p1,p2,p3,p4,p5,p6,p7);

        Map<Customer, List<Order> > orderByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        Map<Customer, Double> totalByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(order -> order
                        .getProducts()
                        .stream()
                        .mapToDouble(Product::getPrice)
                        .sum())));

        List<Product> expensiveProducts = products
                .stream()
                .sorted((pro1,pro2) -> (int) (pro2.getPrice() - pro1.getPrice()))
                .limit(3)
                .toList();

        List<Product> expensiveProducts2 = products
                .stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(3)
                .toList();



        Product mostExpensive = products.stream().max(Comparator.comparingDouble(Product::getPrice)).get();

        Double averageCost = orders.stream().collect(Collectors.averagingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum()));
        Map<String, Double> costByCat = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));

        System.out.println(orderByCustomer);
        System.out.println(totalByCustomer);
        System.out.println("Prodotto più costoso " + mostExpensive);
        System.out.println("Lista dei più costosi");
        System.out.println(expensiveProducts);
        System.out.println("Lista dei più costosi");
        System.out.println(expensiveProducts2);
        System.out.println(averageCost);
        System.out.println("Costo per categoria");
        System.out.println(costByCat);
    }

}
