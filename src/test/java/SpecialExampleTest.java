import com.itiancai.unitexample.model.Product;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by pansen on 15/4/23.
 */
@RunWith(PowerMockRunner.class)
public class SpecialExampleTest {

  /**
   * 普通模拟（不用PowerMock的特殊功能的话，就不用添加@PrepareForTest注释）
   */
  @Test
  public void testCallArgumentInstance() throws Exception {
    File file = PowerMockito.mock(File.class);
    //待测类
    SpecialExample underTest = new SpecialExample();
    PowerMockito.when(file.exists()).thenReturn(true);
    Assert.assertTrue(underTest.callArgumentInstance(file));
  }

  /**
   * mock依赖服务
   */
  @Test
  public void testCallDependencyService() throws Exception {
    ClassDependency classDependency = PowerMockito.mock(ClassDependency.class);
    //待测类
    SpecialExample underTest = new SpecialExample();
    underTest.classDependency = classDependency;
    PowerMockito.when(classDependency.getProduct("s")).thenReturn(new Product("z", "z"));
    Assert.assertFalse(underTest.callDependencyService("s"));
    PowerMockito.when(classDependency.getProduct("s")).thenReturn(new Product("s", "s"));
    Assert.assertTrue(underTest.callDependencyService("s"));
    //用来判断被mock的对象方法是否被调用过，调用过几次。
    Mockito.verify(classDependency, Mockito.times(2)).getProduct("s");
    //classDependency.getProduct("z") 没有被调用过，报错
    //Mockito.verify(classDependency).getProduct("z");
  }

  /**
   * mock内部new出的对象
   */
  @Test
  @PrepareForTest(SpecialExample.class)
  public void testCallInternalInstance() throws Exception {
    File file = PowerMockito.mock(File.class);//mock单元测试方法中new出来的对象
    //待测类
    SpecialExample underTest = new SpecialExample();
    PowerMockito.whenNew(File.class).withArguments("bbb").thenReturn(file);
    PowerMockito.when(file.exists()).thenReturn(true);
    Assert.assertTrue(underTest.callInternalInstance("bbb"));
  }

  /**
   * mock final方法
   */
  @Test
  @PrepareForTest(ClassDependency.class)
  public void testCallFinalMethod() throws Exception {
    ClassDependency depencency = PowerMockito.mock(ClassDependency.class);
    //待测类
    SpecialExample underTest = new SpecialExample();
    PowerMockito.when(depencency.isAlive()).thenReturn(true);//isAlive为final方法
    Assert.assertTrue(underTest.callFinalMethod(depencency));
  }

  /**
   * mock静态方法
   */
  @Test
  @PrepareForTest(ClassDependency.class)
  public void testCallStaticMethod() throws Exception {
    //待测类
    SpecialExample underTest = new SpecialExample();
    PowerMockito.mockStatic(ClassDependency.class);
    PowerMockito.when(ClassDependency.isExist()).thenReturn(true);
    Assert.assertTrue(underTest.callStaticMethod());
  }

  /**
   * mock私有方法
   */
  @Test
  @PrepareForTest(SpecialExample.class)
  public void testCallPrivateMethod() throws Exception {
    //待测类
    SpecialExample underTest = PowerMockito.mock(SpecialExample.class);
    PowerMockito.when(underTest.callPrivateMethod()).thenCallRealMethod();
    PowerMockito.when(underTest, "isExist").thenReturn(true);
    Assert.assertTrue(underTest.callPrivateMethod());
  }

  /**
   * mock系统类的静态和final方法
   */
  @Test
  @PrepareForTest(SpecialExample.class)
  public void testCallSystemFinalMethodAndStaticMethod() throws Exception {
    //待测类
    SpecialExample underTest = new SpecialExample();
    PowerMockito.mockStatic(System.class);
    PowerMockito.when(System.getProperty("aaa")).thenReturn("bbb");
    Assert.assertEquals("bbb", underTest.callSystemStaticMethod("aaa"));
  }

  /**
   * 测试混合，既测试new对象，又测试静态方法
   */
  @Test
  @PrepareForTest({SpecialExample.class, ClassDependency.class})
  public void testMix() throws Exception {
    ClassDependency depencency = PowerMockito.mock(ClassDependency.class);
    File file = PowerMockito.mock(File.class);//mock单元测试方法中new出来的对象
    //待测类
    SpecialExample underTest = new SpecialExample();
    underTest.classDependency = depencency;
    PowerMockito.when(depencency.isAlive()).thenReturn(true);//isAlive为final方法
    PowerMockito.whenNew(File.class).withArguments("bbb").thenReturn(file);
    PowerMockito.when(file.exists()).thenReturn(true);
    Assert.assertTrue(underTest.mix("bbb"));
    Mockito.verify(file).exists();//调用过一次
    Mockito.verify(depencency).isAlive();//调用过一次
    Mockito.verify(depencency, Mockito.never()).getProduct("s");//从来没有调用过该方法
  }
}