package com.lstfight.dao.config;

import com.lstfight.dao.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>使用ComponentScan可以扫描定义的配置或组件</p>
 *
 * @author 李尚庭
 *
 */
@ComponentScan(basePackages = "com.lstfight.*")
@Import(DaoConfig.class)
public class AppConfig {

    @Autowired
    TestDao testDao;

    public static void main(String[] args) {
        //创建容器时需要指定配置文件作为入口 当前为类配置
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class,DaoConfig.class);

        AppConfig appConfig = applicationContext.getBean(AppConfig.class);

        try {
            appConfig.deleteTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        applicationContext.close();

    }

    /**
     * 这里的注解 是需要基于类的代理嘛
     */
    @Transactional(rollbackFor = Exception.class)
    protected void deleteTransaction() throws Exception {
        //TestEntity testEntity1 = new TestEntity(13,new Date(),new Byte[]{1,1,0,1},DirectConstant.Gender.FEMALE,Period.parse("P1Y2M3D"));
        //baseService.deleteById(testEntity1);
        //testDao.delete(6);
        //testDao.save(testEntity1);
        //testDao.deleteAllById(16);
        //System.out.println(testDao.findById(12).getGender().getGender());
        //System.out.println(testEntity1.getId());
        //testDao.save(testEntity1);
    }

}
