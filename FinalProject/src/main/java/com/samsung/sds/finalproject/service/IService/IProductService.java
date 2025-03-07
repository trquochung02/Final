package com.samsung.sds.finalproject.service.IService;

import com.samsung.sds.finalproject.entity.Product;
import java.util.List;

public interface IProductService {
    List<Product> searchProducts(String name);
    Product getProductById(Long id);

    List<Product> getAllProducts();
}
