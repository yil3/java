
import com.yil3.instance.CarInstance;
import com.yil3.pojo.Car;
import com.yil3.pojo.Student;
import com.yil3.pojo.Student2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test1 {
    @Test
    public void Test1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springConfig.xml");

        Student student = (Student) applicationContext.getBean("student1") ;

        Student2 student2 = (Student2) applicationContext.getBean("student2");
        System.out.println(student);
        System.out.println(student2);
    }

    @Test
    public void Test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springConfig.xml");
        Student2 stu = (Student2) applicationContext.getBean("stu");
        System.out.println(stu);
    }

    @Test
    public void Test3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springConfig.xml");
        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);
    }

    @Test
    public void Test4(){
//        CarInstance carInstance = new CarInstance();
////        Car car = carInstance.getCar(2);
////        System.out.println(car);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springConfig.xml");
        Car car2 = (Car) applicationContext.getBean("car2");
        System.out.println(car2);
    }
}
