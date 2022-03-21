package com.lifeifan.service.impl;

import com.lifeifan.mapper.ProductTypeMapper;
import com.lifeifan.pojo.ProductType;
import com.lifeifan.pojo.ProductTypeExample;
import com.lifeifan.service.ProductTyeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductTyeServiceImpl")
public class ProductTyeServiceImpl implements ProductTyeService {
    @Autowired
    ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
