package com.dailycodework.dreamshops.request;

import com.dailycodework.dreamshops.model.Category;
import com.dailycodework.dreamshops.model.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class AddProductRequest {
//    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;
}
