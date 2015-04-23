import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	 * 普通模拟
	 * @throws Exception
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
	 * mock内部new出的对象
	 * @throws Exception
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
	 * @throws Exception
	 */
	@Test
	@PrepareForTest(ClassDependency.class)
	public void testCallFinalMethod() throws Exception {
		ClassDependency depencency =  PowerMockito.mock(ClassDependency.class);
		//待测类
		SpecialExample underTest = new SpecialExample();
		PowerMockito.when(depencency.isAlive()).thenReturn(true);//isAlive为final方法
		Assert.assertTrue(underTest.callFinalMethod(depencency));
	}

	/**
	 * mock静态方法
	 * @throws Exception
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
	 * @throws Exception
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
	 * @throws Exception
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
}