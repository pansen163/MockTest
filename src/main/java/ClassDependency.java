import com.itiancai.unitexample.model.Product;

/**
 * Created by pansen on 15/4/23.
 */
public class ClassDependency {

  public final boolean isAlive() {

    // do something

    return false;

  }

  public static boolean isExist() {

    // do something

    return false;

  }

  public Product getProduct(String s){

    return new Product(s,s);
  }
}