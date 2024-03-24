
										JVM Architecture Part- 4|| nead of customized classloader
									================================================================

==============================================
       Need of Customized class Loader.
==============================================
													
	-> Default class loaders will load .class file only once even though we are using multiple time that class in our 
	   program.
	   
	-> After loading .class file if it is modified outside then default class loader won't load updated version of class
	   file(because .class file already available in method area).
	   
	-> We can resolve this problem by defaning our own customized class loader.

	-> The main advantage of customized class loader is we can controll class loading mechanisam based on our requeriment 
	   for example we can load .class file Separatly every time so that updated version available to our program.
    														
													
	1. Default class loading System 												
														program 
											 ------------------------------	
		                                     |             				  |
									         |              			  |
		------------------------   load 	 |Student  s1 = new Student();|	
		|Looad Student .class  |------------>|Student  s2 = new Student();|	
		|	file 			   |	use      |Student  s3 = new Student();|
		|					   |<----------- |Student  s4 = new Student();|
		|					   |	use      |.							  |
		|					   |<----------- |.							  |
		|					   |     use     |.							  |
		|					   |<----------- |.							  |
		|					   |	 use     |.							  |
		|					   |<----------- |Student s100 = new Student()|		
		------------------------	         | 			  				  |
			                                 |              			  |
			                                 ------------------------------
			
			
	2. Customized class loading System .		
			
														program
											 ------------------------------	
		                                     |             				  |			 -----------------------------------
        							         |              			  |          |							       |	   
        ------------------------   load 	 |Student  s1 = new Student();|<---------|                                 |
        |Looad Student .class  |------------>|Student  s2 = new Student();|          |                                 |
        |	file 			   |	use      |Student  s3 = new Student();|--------->|Check whether Student.class file |   
        |					   |<----------- |Student  s4 = new Student();|    		 |is Modified or not if it is 	   |
        |					   |	use      |.							  |--------->|Modifies load updated class file |  	
        |					   |<----------- |.							  |    		 |If it is not modified use already|	
        |					   |     use     |.							  |    		 |loaded class file 	           |
        |					   |<----------- |.							  |--------->|                                 |
        |					   |	 use     |.							  |          |                                 |
        |					   |<----------- |Student s100 = new Student()|--------->|                                 |
        ------------------------	         | 			  				  |          -----------------------------------
          already loaded .class              |              			  |    					Modified .class file 
        	     file                        ------------------------------    				
            
	
	-> We can define our own customized class loader by extending java.lang.ClassLoader class.
	
		----------------------------------------
		Psuedo code for customized class loader 
		----------------------------------------

			public class CustClassLoader extends ClassLoader
			{
				public Class  loadClass(String cname)throws ClassNotFoundException
				{
					check for updated and 
					load updated .class 
					file and return 
					corresponding class 
					Class object 
				}
			}
			class Client 
			{
				public static void main(String args[])
				{
					Dog d1 = new Dog(); // First time loaded by default class loader
					CustClassLoader cl = new CustClassLoader();
					.
					.
					.
					.
					cl.loadClass("Dog");// Second time loaded by Customized class loader.
					.
					.
					.
					.
					cl.loadClass("Dog");
				}
			}
					
	Note: 

		- While designing or developing Web Server and Application Servers useally we can go for Customized class loaders 
		  to customized class loading Mechanisam.
		  
		Q. What is need or use of classloader class ?
		
			- We can use java.lang.ClassLoader class to define our own Customized class Loaders.
			
			- every class loader in java should be child class of java.lang.ClassLoader class Either directly or 
			  indirectly hence this class access base class for all customized class loaders.
			  
			  
===========================================================================================================================

														Module - 2 
													 ===============

 ==========================================
  Various Memory Areas prasent inside  JVM 
 ==========================================

  -> Whenever JVM loads and runs a java program it needs memory to store several things like byte code , Object, variable
	 ect. 
	 
  -> Total JVM memory are organized into the following 5 catgories. 
 
		1. Method Area.
        2. Heap Area.
        3. Stack Area.
        4. PC Registers.
        5. Native Method Stacks

   -------------
    Method Area 
   -------------
	
	  -> For every JVM one method area will be available.
	  
	  -> Method area will be created at the time of startup.
	  
	  -> Inside method area class level binary data including static variables will be stored. 
	  
	  -> Constants pools of a class will be stored inside method area.
	  
	  
	  

							-------------------------------
                            |             				  |
                            |   -------   ------- ------- |
                            |	|class|	  |class| |class| |
                            |	|level|	  |level| |level| |
                            |	|data |	  |data | |data | |
                            |	-------	  ------- ------- |
                            |							  |
                            |	-------	 ------- -------  |
                            |	|class|	 |class| |class|  |
                            |	|level|	 |level| |level|  |
                            |	|data |	 |data | |data |  |
                            |	-------	 ------- -------  |
                            |							  |
                            |              			      |
                            ------------------------------
									Method ares 

		-> Method area can be access by multiple threads simultaneously and it is not thread safe.	
		 
		
		
		







 
														
			
		
		