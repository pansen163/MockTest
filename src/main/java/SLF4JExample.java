import model.ModelExa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pansen on 15/4/24.
 */
public class SLF4JExample {
	public static Logger logger = LoggerFactory.getLogger(SLF4JExample.class);

	public static void main(String[] args) {
		String arr[] = new String[3];
		arr[0]="嫦娥一号";arr[1]="嫦娥二号";arr[2]="嫦娥三号";

		logger.debug("简单例子");//普通打印
		logger.debug("打印对象{}",new ModelExa());//打印对象就是调用toString方法
		logger.debug("带参数,参数1{}，参数2{}",arr[0],arr[1]);//少于两个参数的时候可以直接使用
		logger.debug("带参数,参数1{}，参数2{}，参数3{}",arr);//多余两个参数可以使用数组
		logger.error("带参数,参数1{}，参数2{}，参数3{}",arr);
	}
}
