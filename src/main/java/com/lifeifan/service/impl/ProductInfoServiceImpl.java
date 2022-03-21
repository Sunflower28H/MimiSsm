package com.lifeifan.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifeifan.mapper.ProductInfoMapper;
import com.lifeifan.pojo.ProductInfo;
import com.lifeifan.pojo.ProductInfoExample;
import com.lifeifan.pojo.vo.ProductInfoVo;
import com.lifeifan.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.stereotype.Service;

import java.util.List;

//Service注解，交给spring创建并管理该对象
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    //自动注入,数据访问层对象
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        //无需条件查询,无需向封装对象添加条件，直接创建空的
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    //分页显示商品
    @Override
    public PageInfo splitpage(int pageNum, int pageSize) {
        //使用分页插件工具类完成分页设置pageNum当前为第几页，pageSize每页几条数据
        PageHelper.startPage(pageNum, pageSize);
        /**
         * 进行PageInfo的封装
         * 因为后面涉及到数据的插入操作，所以需要将显示的数据先安主键降序排序以便将新插入的数据显示在第一行
         * 需要用到条件查询，必须创建对应的条件封装对象ProductInfoExample拼接查询
         * select * from prod order by id desc
         */
        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("p_id desc");
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        //将查询结果集合封装到PageInfo中
        PageInfo<ProductInfo> PageInfo = new PageInfo<>(list);
        return PageInfo;
    }

    //新增商品
    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    //更新商品时，回显该商品信息
    @Override
    public ProductInfo getByID(Integer id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    //更新商品
    @Override
    public int upProd(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    //单个删除商品
    @Override
    public int deleteOneProd(Integer pId) {
        return productInfoMapper.deleteByPrimaryKey(pId);
    }

    //批量删除商品
    @Override
    public int deleteListProd(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selevtProdVo(ProductInfoVo productInfoVo) {
        return productInfoMapper.selevtProdVo(productInfoVo);
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductInfoVo productInfoVo, int pageSize) {
        PageHelper.startPage(productInfoVo.getPage(), pageSize);
        List<ProductInfo> list = productInfoMapper.selevtProdVo(productInfoVo);
        return new PageInfo<ProductInfo>(list);
    }
}
