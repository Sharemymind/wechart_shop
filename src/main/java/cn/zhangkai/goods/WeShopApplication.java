package cn.zhangkai.goods;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("cn.zhangkai.goods.mapper")
public class WeShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeShopApplication.class, args);
    }

}
