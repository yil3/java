import com.yil3.utils.Cal;
import com.yil3.utils.CalImpl;
import com.yil3.utils.MyInvocationHandler;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {

    @Test //原生切面
    public void test(){
        Cal cal = new CalImpl();
//        cal.add(1,2);
//        cal.sub(3,2);
//        cal.mul(2,2);
//        cal.div(4,2);

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        Cal cal1 = (Cal) myInvocationHandler.bind(cal);
        cal1.add(1,2);
        cal1.sub(3,2);
        cal1.mul(2,2);
        cal1.div(4,2);
    }

    @Test //springAOP
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Cal cal = (Cal) applicationContext.getBean("calImpl");
        cal.add(1,2);
        cal.sub(3,2);
        cal.mul(2,2);
        cal.div(4,2);
    }
}
