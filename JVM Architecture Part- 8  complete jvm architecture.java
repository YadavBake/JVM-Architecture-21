
											JVM Architecture Part- 8 || complete jvm architecture
										===========================================================


  -------------
  |java source|				      -------------------------------------------------------------------------
  |   file    |					  |		    			class Loader Subsystem   		  				  |
  |	(.java)   |					  -------------------------------------------------------------------------
  -------------	 				  |		----------------------   ----------------                         |
		|						  |		|    Loading         |	 |     Linking 	|                         |
		|						  |		| ---------------    |   |  ----------- |                         |
		|                         |     | |Bootstrap C.L|    |   |  |  Verify | |	                      |
		|                         |     | ---------------    |   |  ----------- |	--------------------- |
		|            |===========>|     |       ^ ^ |        |   |	     |      |	| Initialization    | |
		|            |            |     |       | | |        |   |	-----------	|==>|------------------ | |
		|            |            |     |  ---------------   |==>|  | prepare | |   | | Initialization| | |
		|            |            |     |  |Extension C.L|   |   |  ----------- |   | ----------------- | |
  ---------------	 |            |     |   ---------------  |   |		 |      |   --------------------- |
  |java compiler| 	 | 		      |		|	  ^	^ |          |   |  ----------- |                         |
  |	 (javac)    |	 | 		      |		|	  |	| |	         |   |  |  Resolve| |                         |
  ---------------	 | 		      |		|  ------------------|   |  ----------- |                         |
		|			 | 		      |		|  | Application C.L|| 	 ----------------                         |
		|			 | 		      |		|  ------------------|	                                          |
		|            |            |     ----------------------                                            |
		|            |            |                                                                       |
		|			 | 		      -------------------------------------------------------------------------
		|            | 								 ||			 ^^
		|            | 								 ||			 ||
  ---------------	 | 								 ||          ||
  |java class   |    |                               ||          ||
  |   file      |====| 								 ||	         ||
  |(.class file)|                                    ||          ||
  ---------------   								 ||	         ||
											  Various||   Memory ||Of  JVM 			
  ------------------------------------------------------------------------------------------------------------------------------------------  
  |                     																												   |
  |	--------------------- --------------------- ------------------------------- ------------------------- ------------------------------   |
  |	| -------   ------- | | --------  ------- | |                             | |                       | |                            |   |
  |	| |class|   |class| | | |Object| |Object| | | |   t1..   | |     tn   |   | |  -------------------- | | |  t1  | |  t2  | |   tn | |   |
  |	| |level|   |level| | | |level | |level | | | |          | |          |   | |  |PC Register for t1| | | |      | |      | |      | |   |
  |	| |Data |   |Data | | | |Data  | |Data  | | | |----------| |----------|   | |  -------------------- | | |------| |------| |------| |   |
  | | -------   ------- | | -------  -------  | | |          | |          |   | |  -------------------- | | |      | |      | |      | |   |
  | | -------   ------- | | -------  -------  | | |----------| |----------|   | |  |PC Register for t2| | | |------| |------| |------| |   |
  | | |class|   |class| | | |Object| |Object| | | |          | |         <--| | |  -------------------- | | |      | |      | |      | |   |
  | | |level|   |level| | | |level|  |level | | | |----------| |----------| | | |  -------------------- | | |------| |------| |------| |   |
  | | |Data |   |Data | | | |Data |  |Data  | | | |LVA|OS|DF|| |LVA|OS|DF|| | | |  |PC Register for tn| | | |      | |      | |      | |   |
  | | -------   ------- | | -------  -------  | | |----------| |----------| | |	|  -------------------- | | |------| |------| |------| |   |
  | --------------------- --------------------- |  						   |  |	|                       | |                            |   |
  |   Method Area             Heap Area         |  		stack Frame<-------|  | ------------------------- ------------------------------   |
  |												|                             |    PC Register 					Native method area                                                           |
  |												-------------------------------                                                            |
  |                                     				Stack Area                                                                                                    |
  ------------------------------------------------------------------------------------------------------------------------------------------  
                   ||						^^
                   ||                       ||
                   ||                       ||
                   ||                       ||
                   ||                       ||
                   ||                       ||
  --------------------------------------------------------
  |				|   		  JIT Compiler          |    |
  |		  		|			 ==============		    |    |		
  |             |                                   |    |		
  |				|	 ----------------	----------- |    |		   ------------	  -----------------
  |				|	 | Intermediate |	|profiler | |    |	       |		  |   | 			  |
  |				|	 |Code generater| 	----------- |    |         | java	  |   |				  |
  | Interpreter |	 ----------------	            |----|========>| native   |-->|               |	
  |				|		 | Intermediate Code        ||GC||         | interface|   | Native Method |
  |				|		 |                          |----|         |          |   |    Library    |
  |				|  ----------------                 |... |<========|     (JNI)|<--|               |
  |				|  |Code optimizer|                 |    |         |          |   |               |
  |				|  ---------------- 		        |    |         ------------   -----------------
  |				|		 |                          |    |
  |				|		 |                          |    |
  |				|-----------------------            |    |
  |				||Target Code generater|            |    |
  |				|-----------------------	        |    |
  |				|		 |                          |    |
  |				|		 |                          |    |
  |				|	--------------		            |    |
  |				|	|Machine Code|                  |    |
  |				|	--------------                  |    |
  |             |                                   |    |
  --------------------------------------------------------
  					Execution Engine 
					
					
========================
  class File Structure 	
========================


	class File
	{
		
		magic_number;
		minor_version;
		major_version;
		constant_pool_count;
		constant_pool[];
		access_flags;
		this_class;
		super_class;
		interface_count;
		interface[];
		fields_count;
		fields[];
		methods_count;
		methods[];
		attributes_count;
		attributes[];
	}
	
	
   --------------	
	magic_number
   --------------

	 -> The four bytes of the class file is a magic number this is pre-defined value used by JVM to identify .class file 
		generated by valid compiler or not.
		
	 -> The value should be 0xCAFEBABE.
	 
	 Note: 
		
		- Whenever we are executing a java class if JVM unable to find valid magic_number then we will Runtime Exception 
		  saying java.lang.ClassFormatError: Incompatible magic value.
	 
   ---------------------------------
	minor_version and major_version	
   ---------------------------------

	-> major_version and minor_version reprasent .class file version.
	
	-> JVM will use this versions to identify which version of compiler generates the current .class file.
	
					M.m
					| |----------->minor_version
				major_version	
				
		1.5v 1.6v 1.7v   
		49.0 50.0 51.0
		
	Note: 
	
	  - Lower version compiler generated .class files can be run by higher version JVM.
	  
	  - But higher version compiler generated .class file can't be run by lowe version JVMs if we are trying to run 
		we will get Runtime Exception saying jav.lang.UnsupportedClassVersionError.
		
		
			javac 1.6v ===> java 1.7v //valid 
			
			javac 1.7v ====> java 1.6v //RE: UnsupportedClassVersionError
			
	--------------------
	constant_pool_count	
	--------------------
	
		-> It reprasent number of constant present in constant_pool.
		
	-----------------	
	constant_pool[] :
	-----------------
	
		-> It reprasent information about constant present in constant_pool.
		
	------------	
	access_flags
	------------
	 
	  -> It provide information about modifiers which are declare to the class. 
	
	-----------	
	this_class 
	-----------
  
      -> It reprasent fully qualified name of the class.

    -----------
	super_class
	------------
		
		-> It reprasent fully qualified name of immediate super class of current class. 
		
	
	
		----------
		|		 |	
		|		 |	this_class : Test 
		|        |
		|        |	super_class : Object 
		|        |
		----------
		  Test.class 

   -----------------			
	interface_count
   -----------------	

	 -> It returns number of interfaces implemented by current class.
	 
	------------ 
	interface[] 
	------------
	
	 -> It returns interface information implemented by current class.
	
	------------
	fields_count
	------------
	
	 -> It reprasent number fields prasent in the current class. 
	 
	-------- 
	fields[]
	--------
	
	 -> It reprasent fields information prasent in current class.
	 
	-------------
	methods_count
	-------------
	
	 -> It reprasent number of method prasent in current class. 
	 
	--------- 
	methods[]
	---------
	
	 -> It provides information about all method prasent in current class.
	 
	---------------- 
	attributes_count
	----------------
	
	 -> It returns number attributes prasent in current class. 
	 
	------------ 
	attributes[]
	------------
	
	 -> IT provides information about all attributes prasent in current class.
	 
	 
	 javap -verbose Test.class 
	