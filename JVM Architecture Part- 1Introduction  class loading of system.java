
									  JVM Architecture Part- 1 Introduction  class loading of system
									==================================================================
									
									

 Virtual Machine 

  Type of Virtual Machines 
	
	1. Hard ware Based VM 
	2. Application Based VM 
	
  Basic Architecture of JVM 
 
  Class Loader SubSystem 
	
	1. Loading
	2. Linking 
	3. Initialization

 Types of class Loaders 
 
	1. Bootstrap class Loader.
	2. Extension class Loader
	3. Application class Loader.
	
 How class Loader works 
 
 What is the need of Customized class Loader.
 
 Psuedo code for customized class loader 
 
 Various Memory Areas of JVM 
 
	1. Method Area.
	2. Heap Area.
	3. Stack Area.
	4. PC Registers.
	5. Native Method Stacks
	
 Program to display heap memory statistics.
 
 How to set Maximum and Minimum Heap Size? 
 
 
 Execution Engine 
 
	1. Interpreter.
	2. JIT Compilers.
	
 Java Native Interface (JNI);
 
 Complete Architecture Diagram of JVM 
 
 class File Structure.


==========================================================================================================================
									
=====================
   Virtual Machine 	
=====================
 
												 Virtual Machine 
														|
														|
							------------------------------------------------------------------
							|																 |
						Hard-ware Based|System Based 						   Application Based| Process Based 
 

 -> It is software simulation of a machine which can perform operations like a physical machine.

 -> There are two type of Virtual machines 

		1. Hard-ware or System based  Virtual Machine .
		
		2. Application Based or process Based  Virtual Machine.
		
-------------------------------------------
 Hard-ware or System Based Virtual Machine 
-------------------------------------------

 -> It provides several logical systems on the same computer with strong Isolation from each other. That is on one 
	physical machine we are defining multiple logical machines.
	
 -> The main advantage of Hard-ware based virtual Machines is Hard-ware resources sharing and improve utilization of 
	Hard-ware resources.
	
	    Ex. 
		
			KVM [Kernal Based Virtual Machine for Linux Systems]
			
			VMWare, Xen, Cloud Computing ect.
			
-----------------------------------------------------			
  Application Based or Process Based Virtual Machine 
-----------------------------------------------------


 -> This virtual machine access Runtime engines to run a particular programming langauges applications.


	EX. 
	
		JVM(Java Virtual Machine) access Runtime engine to run java based applications.
		
		PVM(Perate Virtual Machine) access Runtime engine to run perl based Applications.
		
		CLR(Command Langauge Runtime) access Runtime engine to run .Net based Applications.
		
		
-----
 JVM 
----- 

 -> JVM is the part of JRE and it is responsible to load and run java class files.
 
 
------------------------------------ 
 Basic Architecture Diagram Of JVM 
------------------------------------


											---------------------
											|	class Loader	|
					.class===============>  |	SubSystem		|
					  file					---------------------
													|^
													||
								various Memory 		|| area of JVM 
	-----------------------------------------------------------------------------------------------------------------	
	|																												| 
	|----------------		----------------  		----------------       ----------------       ----------------  |
	||	 Method     |       |	  Heap     |        |     Stack    | 	   | Pc Registors |	      |	Native Method|  |
	||	  Area 		|       |	  Area 	   |        |	  Area     |       |			  |	      |	    Stack    |  |
	|----------------       ----------------        ----------------       ----------------       ----------------  |
	|                                                                                                               |
	-----------------------------------------------------------------------------------------------------------------
								|   ^
								|   |
								|	|
							----------------		-----------------			----------------
							|	 Execution |------->| Java Native   |---------->|Native Method |
							|	   Egine   |<-------| Interface(JNI)|<----------|	  Library  |
							----------------        -----------------           ----------------
							
							
-------------------------
 Class Loader SubSystem	
-------------------------

 -> class Loade SubSystem is responsible for the following 3 activities.

		1. Loading.
		2. Linking. 
		3. Initialization.
		

   ---------
	Loading 
   ---------

	 -> Loading means reading class files and store corrusponding binary data in method area.
	 
	 -> For each class file JVM will store corrusponding information in the method area.
	 
			1. Fully qualified name of class.
			2. Fully name of immediate parent class.
			3. method information.
			4. Variables information.
			5. Constructor information.
			6. Modifiers information.
			7. Constant pool information. etc.
						
	
	 -> After Loading .class file immediatly JVM creates an object for that loaded class on the Heap Memory of type 
		java.lang.Class
						
						---------------------				---------------------------
						| -------------	    |				| ----------------------  |
	-----------	        | | Student   |     |               | |class class object  |  |
	| Student |------->	| |.class     |	    |               | |to reprasent student|--|---> It is not Student object
	|.class   |         | |information|     |------------>  | |.class information  |  |	    and it is class Class Object 
	-----------	        | -------------     |               | ----------------------  |
	                    |                   |               |                         |
	-----------			| -------------	    |               | ----------------------  |
    |Customer |------->	| | Customer  |     |               | |class class object   | |
    |.class   |         | |.class     |	    |------------>  | |to reprasent Customer|-|---> It is not Student object 
    -----------	        | |information|     |               | |.class information   | |	    and it is class Class Object
     Stored in          | -------------     |               | ----------------------  |
     Hard Disk          |      				|               |      				      |
                        |      				|               |      				      |
						|	                |               |	                      |
						---------------------               ---------------------------
							Method Ares(JVM)					Heap Aread (JVM)
							
							
	
	 -> The class Class object can be used by programmer to get class level information like methods information or 
		variable information, constructors information etc.
		
		Ex.
		
		import java.lang.reflect.*;
		class Student 
		{
			public String getName()
			{
				return null;
			}
			public int getRollno()
			{
				return 10;
			}
		}
		
		class Test 
		{
			public static void main(String args[]) throws Exception 
			{
				int count = 0 ;
				//Class c = Class.forName("java.lang.Student");
				//Class c = Class.forName("java.lang.String");
				Class c = Class.forName("java.lang.Object");
				Method [] m = c.getDeclaredMethods();
				for(Method m1 :m)
				{	
					count++;
					System.out.println(m1.getName());
				}
				System.out.println("The number of methods :" +count);
			}
		}


  Note: 
  
	-> 	For every loaded type only one class object will created even though we are using class multiple times in our 
		programe.
		
		Ex. 
		import java.lang.reflect.*;
		class Student 
		{
			public String getName()
			{
				return null;
			}
			public int getRollno()
			{
				return 10;
			}
		}

	class Test2 
	{
			public static void main(String args[]) throws Exception 
			{
				Student s1 = new Student();
				Class c1 = s1.getClass();
				Student s2 = new Student();
				Class c2 = s2.getClass();
				System.out.println(c1.hashCode());
				System.out.println(c2.hashCode());
				System.out.println(c1 ==c2);
			}
		
	}
	
	output:
	
		798154996
		798154996
		true
			
			--------------  load    -------------------   Creates 	------------------------------------------------
			|Student.clss|--------->|Method area (JVM)|------------>|class Class Object Students in heap area (JVM)| 
			--------------			-------------------				------------------------------------------------	
			  Hard Disk 											  ^	^
														c1------------|	|
														c1--------------|
														
														
    -> In the above programe even though we are student class multiple time only one class Class object got created.															