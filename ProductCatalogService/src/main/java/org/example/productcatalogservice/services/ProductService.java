package org.example.productcatalogservice.services;

import org.example.productcatalogservice.clients.FakeStoreApiClient;
import org.example.productcatalogservice.dtos.FakeStoreProductDto;
import org.example.productcatalogservice.models.Category;
import org.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreApiClient.getAllProducts();
        return fakeStoreProductDtos.stream().map(this::getProductFromFakeStoreProductDto).toList();
    }


    @Override
    public Product getProductById(Long productId) {
        if (productId == null || productId <= 0 || productId > 20) {
            throw new RuntimeException("Product not found");
        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProductById(productId);
        return getProductFromFakeStoreProductDto(fakeStoreProductDto);
    }


    @Override
    public Product createProduct(Product product) {
        if(product.getId()!= null && product.getId()<=20)
            throw new RuntimeException( "Product id must be greater than 20" );
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.createProduct(getFakeStoreProductDtoFromProduct(product));
        return getProductFromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        if(productId!= null && productId<=20)
                throw new RuntimeException( "Product id must be greater than 20" );
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.replaceProduct(productId, getFakeStoreProductDtoFromProduct(product));
        return getProductFromFakeStoreProductDto(fakeStoreProductDto);
    }

    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImgUrl());
        if(product.getCategory() != null){
            fakeStoreProductDto.setCategory(product.getCategory().getCategoryName());
        }
        return fakeStoreProductDto;
    }


    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImgUrl(fakeStoreProductDto.getImage());
        if(fakeStoreProductDto.getCategory() != null){
            Category category = new Category();
            category.setCategoryName(fakeStoreProductDto.getCategory());
            product.setCategory(category);
        }
        return product;
    }
}
