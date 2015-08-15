import com.itiancai.unitexample.model.Product;

import java.io.File;

/**
 * Created by pansen on 15/4/23.
 */
public class SpecialExample {

  //模拟注入服务
  ClassDependency classDependency=new ClassDependency();

  //mock普通方法
  public boolean callArgumentInstance(File file) {
    return file.exists();
  }

  //mock依赖服务
  public boolean callDependencyService(String s){
    Product product = classDependency.getProduct(s);
    if(s.equals(product.getWeight())) {
      return true;
    }
    return false;
  }

  //mock方法内部new出来的对象
  public boolean callInternalInstance(String path) {
    File file = new File(path);
    return file.exists();
  }

  //Mock普通对象的final方法
  public boolean callFinalMethod(ClassDependency refer) {
    return refer.isAlive();
  }

  //Mock普通类的静态方法
  public boolean callStaticMethod() {
    return ClassDependency.isExist();
  }

  //Mock 私有方法
  public boolean callPrivateMethod() {
    return isExist();
  }

  private boolean isExist() {
    return false;
  }

  //Mock系统类的静态和final方法
  public String callSystemStaticMethod(String str) {
    return System.getProperty(str);

  }

  public boolean mix(String path){
    File file = new File(path);
    if(!file.exists()){
      return false;
    }
    if(!classDependency.isAlive()){
      return false;
    }
    return true;
  }

}




