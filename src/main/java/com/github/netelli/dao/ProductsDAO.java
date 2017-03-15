package com.github.netelli.dao;

import com.github.netelli.model.pojo.Brand;
import com.github.netelli.model.pojo.Product;

public abstract class ProductsDAO implements BaseDAO<Product>{
    public abstract void updateBrandId(Product product, Brand brand);

    public abstract void remove(Product product);
}
