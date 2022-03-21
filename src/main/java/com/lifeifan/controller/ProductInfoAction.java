package com.lifeifan.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lifeifan.pojo.ProductInfo;
import com.lifeifan.pojo.vo.ProductInfoVo;
import com.lifeifan.service.impl.ProductInfoServiceImpl;
import com.lifeifan.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    String saveFileName = "";
    private final int pageSize=5;

    @Autowired
    ProductInfoServiceImpl productInfoServiceImpl;

    /**
     * 不分页显示全部商品
     */
    @RequestMapping("/getall")
    public String getAll(HttpServletRequest request) {
        List<ProductInfo> allProdList = productInfoServiceImpl.getAll();
        request.setAttribute("list", allProdList);
        PageHelper.startPage(1, pageSize);
        return "product";
    }

    //显示第一页的五条数据
    @RequestMapping("/split")
    public String split(HttpServletRequest request) {
        //得到第一页的数据
        PageInfo info = productInfoServiceImpl.splitpage(1, pageSize);
        request.setAttribute("info", info);
        return "product";
    }

    //ajax提交分页请求实现翻页功能
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public String ajaxSplit(ProductInfoVo productInfoVo, HttpSession session) {
        session.setAttribute("info",productInfoServiceImpl.splitPageVo(productInfoVo, pageSize));
        return "product";
    }

    //新增商品上传图片实现
    @ResponseBody
    @RequestMapping("/ajaxImg")
    //MultipartFile专门用来进行当前上传文件流对象的接收
    //方法参数名称与ajax请求中name、id属性一致
    public Object adaxImg(MultipartFile pimage, HttpServletRequest request) {
        //提取生成文件名UUID+上传文件的后缀
        // getOriginalFilename将文件传入自动截取文件的后缀
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //通过request对象得到项目中文件存储的物理路径
        String path = request.getServletContext().getRealPath("/image_big");
        //转存
        //path : 项目中文件存储的物理路径
        //File.separator根据当前操作系统动态获取文件分隔符
        try {
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回客服端JSON对象，封装图片的路径，为了在页面立即回显
        JSONObject object = new JSONObject();
        object.put("imgurl", saveFileName);
        return object.toString();
    }

    //新增商品提交表单实现
    @RequestMapping("/save")
    public String save(HttpServletRequest request, ProductInfo info) {
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int i = -1;
        i = productInfoServiceImpl.save(info);
        if (i > 0) {
            request.setAttribute("msg", "商品增加成功！");
        } else {
            request.setAttribute("mag", "商品增加失败！");
        }
        //清空变量
        saveFileName = "";
        //结束后重新访问数据库，跳转到分页显示页的action
        return "forward:/prod/split.action";
    }

    //修改商品信息，返回显示所有修改的商品的信息
    @RequestMapping("/one")
    public String getByID(Integer pId,ProductInfoVo vo,HttpServletRequest request) {
        ProductInfo info = productInfoServiceImpl.getByID(pId);
        request.setAttribute("vo",vo);
        System.out.println(info.toString());
        request.setAttribute("prod", info);
        return "update";
    }

    //更新商品的实现
    @RequestMapping("/update")
    public String upDate(ProductInfo info, HttpServletRequest request) {
        if (!saveFileName.equals("")) {
            info.setpImage(saveFileName);
            saveFileName = "";
        }
        int i = -1;
        i = productInfoServiceImpl.upProd(info);
        if (i > 0) {
            request.setAttribute("msg", "商品编辑成功！");
        } else {
            request.setAttribute("msg", "商品编辑失败！");
        }
        return "forward:/prod/split.action";
    }

    //实现单个删除商品
    @RequestMapping("/delete")
    public String deleteOneProd(Integer pId, HttpServletRequest request) {
        int i = -1;
        i = productInfoServiceImpl.deleteOneProd(pId);
        if (i > 0) {
            request.setAttribute("msg", "删除成功！");
        } else {
            request.setAttribute("msg", "删除失败！");
        }
        return "forward:/prod/deleteAjax.action";
    }
    @ResponseBody
    @RequestMapping(value = "/deleteAjax", produces = "text/html;charset=UTF-8")
    public Object deleteAjax(HttpServletRequest request) {
        PageInfo info = productInfoServiceImpl.splitpage(1, pageSize);
        request.getSession().setAttribute("info", info);
        return request.getAttribute("msg");
    }
    //批量删除商品
    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids, HttpServletRequest request) {
        //将参数 字符串str 转为 字符串数组
        String[] ps = pids.split(",");
        try {
            int i = productInfoServiceImpl.deleteListProd(ps);
            if (i > 0) {
                request.setAttribute("msg","批量删除成功！");
            }else {
                request.setAttribute("msg","批量删除失败！");
            }
        } catch (Exception e) {
            request.setAttribute("msg","商品不可删除！");
        }
        return "forward:/prod/deleteAjax.action";
    }
}