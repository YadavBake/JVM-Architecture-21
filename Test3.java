class Test3 
		{
			public static void main(String args[])
			{
				System.out.println(String.class.getClassLoader());
				System.out.println(Test.class.getClassLoader());
				System.out.println(Customer.class.getClassLoader());
			}
		}
		
		null
		jdk.internal.loader.ClassLoaders$AppClassLoader@33909752
		jdk.internal.loader.ClassLoaders$AppClassLoader@33909752
	   