package org.example.productcatalogservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice.models.ProductVisibility;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String imgUrl;
    private Double price;
    private CategoryDto category;
    private ProductVisibility visibility;
}
