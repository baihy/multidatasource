import com.baihy.config.DatabaseConfig;
import com.baihy.domain.User;
import com.baihy.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @ProjectName: multidatasource
 * @packageName: PACKAGE_NAME
 * @Description:
 * @author: huayang.bai
 * @date: 2018-12-10 20:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSave() {
        User param = new User();
        param.setId(UUID.randomUUID().toString().replace("-", ""));
        param.setUsername("用户名");
        param.setPassword("password");
        int result = userService.save(param);
        System.out.println(result);
    }

    @Test
    public void testInsert() {
        User param = new User();
        param.setId(UUID.randomUUID().toString().replace("-", ""));
        param.setUsername("用户名");
        param.setPassword("password");
        int result = userService.insert(param);
        System.out.println(result);
    }


}
