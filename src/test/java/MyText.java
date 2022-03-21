import com.lifeifan.mapper.AdminMapper;
import com.lifeifan.mapper.ProductInfoMapper;
import com.lifeifan.pojo.Admin;
import com.lifeifan.pojo.AdminExample;
import com.lifeifan.pojo.ProductInfo;
import com.lifeifan.pojo.vo.ProductInfoVo;
import com.lifeifan.service.AdminService;
import com.lifeifan.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml", "classpath:applicationContext_service.xml"})
public class MyText {
    @Autowired
    ProductInfoMapper mapper;
    @Test
    public void textSelectVo() {
        ProductInfoVo productInfoVo = new ProductInfoVo();
        List<ProductInfo> list = mapper.selevtProdVo(productInfoVo);
        list.forEach(productInfo -> productInfo.toString());
    }

}
