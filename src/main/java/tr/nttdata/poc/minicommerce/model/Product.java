package tr.nttdata.poc.minicommerce.model;

import lombok.*;

@Getter
@Setter
@ToString
public class Product {

    private String id;

    private String name;

    private String description;

    private String imageuri;

    private double price;
}
