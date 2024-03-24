
										JVM Architecture Part- 3|| how class loader works
									=======================================================


 ========================
  How class Loader works 									
 ========================
 
	->  
												-------------------------  Searches in  ------------------------
												|Bootstrap  class Loader|-------------->| Bootstrap class Path |
			                                    -------------------------				| {JDK\JRE\lib) 	   |	
			                                    		^	^	|						------------------------
			                                    delegate|	|	| delegate									
			                                    -------------------------  Searches In  -------------------------
			                                    |Extension class Loader |-------------->| Extension class Path  |
			                                    -------------------------				|  (JDK\JRE\lib\ext)    |
														^	^	|						-------------------------	
												delegate|	|	| delegate							
										request -------------------------- Searchers In -------------------------	
    -------							   |------->|Application class Loader|------------->| Application class Path| 			
	| .  .|							   |		-------------------------				| Environment variable  |		
	|  -  |			--------------     | 	       										|  class Path 			|
	-------	request |class Loader|------          										-------------------------	
	   |   |------>	| Subsystem	 |
	   |   |		--------------
	   ^   |
       |   |	
	   |---
	   ^
	   |	
	  JVM 												
													
													
	-> class Loader follows delegation hirarchy principle or algorithm.

    -> Whenever JVM come across a particular class first it will check whether the corrusponding .class file is already 
	   loaded or not if it is already loaded in method area then JVM will considard that loaded class.
	   
	-> If it is not loaded then JVM request class Loader Subsystem to load that particular class.

	-> Then class loader Subsystem hand ovres the request to Application class Loader.
	
	-> Application class Loader delegates the request to Extension class loader.
	
	-> Which inturn delegates the request to Bootstrap class loader.
	
	-> Then Bootstrap class loader will search in Bootstrap class path if it is available then the corrusponding .class 
	   will be loaded by Bootstrap class loader.
		  
	-> If it is not available then Bootstrap class loader delegates the request to Extension class loader.
	
	-> Extension class loader will search in Extension class apth if it is available then it will be loaded otherwise 
	   extension class loader delegates request to Application class Loader.
	
	-> Application class loader will search in Application class path if it is available the it will be loaded otherwise
	   we will get Runtime exception NoClassDefFoundError or ClassNotFoundException.
	   
	-> 


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
	   
	   
	-> Assume 	Customer.class prasent in both extension and application class path and Test.class prasent in only 
	   application classpath.
	   
	   
	For String.class 
   ==================

	 -> Bootstrap class Loader from Bootstrap classpath.
	 
		o/p: null // Because it is not implemented in java that 's we will get null.
	 
	For Test.class 
  ==================

	 -> Application class loader from Application classpath
	 
		sun.misc.Launcher.$AppClassLoader@1912a56.
		
    For  Customer.class 
  =======================

	 -> Extension class loader from Extension class path.
	 
	 
		sun.misc.Launcher.$ExtClassLoader@1072b90.
		
		
	Note: 

		1. Bootstrap class loader is not java object hence a we got null in the first case.
		
		2. But Extension and Application class loader are java objects hence we are getting corrusponding output for 
		   the remaining two SOPs.
		   
		   
		   [classname@hashCode_in_hexadecimalform]
		   
		3. class loader Subsystem will give the highest priority for Bootstrap classpath and then Extension classpath
		   followed by Application classpath.
		   
		   