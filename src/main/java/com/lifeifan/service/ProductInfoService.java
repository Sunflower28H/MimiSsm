package com.lifeifan.service;

import com.github.pagehelper.PageInfo;
import com.lifeifan.pojo.ProductInfo;
import com.lifeifan.pojo.vo.ProductInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoService {
    //不分页显示商品
    List<ProductInfo> getAll();
    //分页显示实现
    PageInfo splitpage(int pageNum,int pageSize);
    //新增商品表单提交实现
    int save(ProductInfo info);
    //按住键id查询商品
    ProductInfo getByID(Integer id);
    //更新商品
    int upProd(ProductInfo info);
    //单个删除商品
    int deleteOneProd(Integer pId);
    //批量删除商品
    int deleteListProd(String[] ids);
    //条件查询商品返回商品集合
    List<ProductInfo> selevtProdVo(ProductInfoVo productInfoVo);
    PageInfo splitPageVo(ProductInfoVo productInfoVo,int pageSize);
}
