package com.lifeifan.listener;

import com.lifeifan.pojo.ProductType;
import com.lifeifan.service.ProductTyeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ProductTypeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //手动从spring容器中取出ProductTypeServiceImpl对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTyeService productTyeServi = (ProductTyeService) context.getBean("ProductTyeServiceImpl");
        List<ProductType> typeList = productTyeServi.getAll();
        //存入全局作用域，供新增页面、查询页面、前台查询功能提供全部商品类别集合
        servletContextEvent.getServletContext().setAttribute("typeList",typeList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
