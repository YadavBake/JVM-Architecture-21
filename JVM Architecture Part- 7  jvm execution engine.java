
											JVM Architecture Part- 7|| jvm execution engine
										======================================================


===============================
  Execution Engine Module -3  
===============================  

 -> This is the central component of JVM.
 
 -> Execution Engine is responsible to executive Java class files.
 
 -> Execution Engine mainly containes two components :
 
		1. Interpreter.
		2. JIT compiler.
		
    -------------		
	 Interpreter 
	-------------

		-> It is responsible to read byte code and interprete into machine code (Native Code) and execute that machine 
		   code line by line.
		   
		-> The problem with interprete is it interprete every time even same method invoked multiple times which reduces 
		   performance of the system.
		   
		-> To over come this problem sun people introduced JIT compiler in 1.1v version.
		
	-------------- 	
	 JIT Compiler 
	--------------
	
	  -> The mai purpose of JIT compiler is to improve performance internally JIT compiler maintains separate count for 
		 every method.
		 
	  -> Whenever JVM come across any method call first that method interprete normally by the interpreter and JIT 
		 compiler increments the  corrusponding count variable .
		 
	  -> This process will be continued for every method.
	  
	  -> Once a if any method count reaches threasold value then JIT compiler identifies that, Method is repitadly used 
		 method (HOT SPOT).
		 
	  -> Immediatly JIT compiles that method and generates the corrusponding native code.
	  
	  -> Next time JVM came across that method call then JVM uses native code directly and executes it initiated of 
		 interpreting once again so that performance of the system will be improved.
		 
	  -> The threasold count different from JVM to JVM.
	  
	  -> Some advanced JIT Compilers will re-compile generates native code if count reaches threasold value seconed time so 
		 that more optimized machine code will be  generated.
		 
	  -> Internally Profiler, which is the part of JIT compiler is responsible to identify HOT SPOTs.
	  
	  
 Note: 
 
	1. JVM interprete total program at list once.
	
	2. JIT compilation is applicable only for repitadly required methods not for every method.
	
	
		---------------------------------------------------------------------------
		|				|     JIT Compiler							  |			  |
        |		  		|	 ==============		                      |           |
        |               |                                             |           |
        |				|	 ----------------		     -----------  |           |
        |				|	 | Intermediate |			 |profiler |  |	          |
        |				|	 |Code generater| 			 -----------  |           |
        | Interpreter 	|	 ----------------	                      |   ------  |
        |				|		 | Intermediate Code                  |	  |	GC |  |
        |				|		 |                                    |   ------  |
		|				|  ----------------                           |  ....     |
        |				|  |Code optimizer|                           |           |
		|				|  ---------------- 		                  |           |
        |				|		 |                                    |           |
        |				|		 |                                    |           |
		|				|-----------------------                      |           |
        |				||Target Code generater|                      |           |
		|				|-----------------------	                  |           |
        |				|		 |                                    |           |
		|				|		 |                                    |           |
		|				|	--------------						      |           |
		|				|	|Machine Code|                            |           |
		|				|	--------------                            |           |
		|               |                                             |           |
		---------------------------------------------------------------------------
							Execution Engine 
							
	
  =============================	
   Java Native Interface(JNI)
  =============================
	
	-> JNI access mediator for java method calls and corrusponding native libraries that is JNI is responsible to 
		provide information about native libraries to the JVM.
		
	-> Native method Library provides or holds native libraries information.


	
		-----------------		-----------------			-----------------
        |				|       |				|           |				|
        |				|  		|				|           |				|
        |               |------>|               |---------->|               |
        |  Execution    |       |  java native  |           | Native Method |
        |    Engine     |       |    interface  |           |    Library    |
        |               |<------|     (JNI)     |<----------|               |
        |               |       |               |           |               |
        -----------------       -----------------           -----------------