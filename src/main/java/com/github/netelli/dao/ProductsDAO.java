package com.github.netelli.dao;

import com.github.netelli.model.pojo.Brand;
import com.github.netelli.model.pojo.Product;

/**
 * Created by user on 05.02.2017.
 */
public abstract class ProductsDAO implements BaseDAO<Product>{
    public abstract void updateBrandId(int productId, Brand brand);
}
