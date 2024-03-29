
													JVM Architecture Part- 6 || stack memory
												=============================================


=====================
  3. Stack Memory 	
=====================

 -> For every thread JVM will create a separate stack at the time of thread creation.
 
 -> Each and every method perfomed by that thread will be stored in the stack including locale variables also.
 
 -> After compliting a method the corrusponding entry from the stack will be removed. After compliting all method
	calls the stack will become empty and that empty stack will be destroyed by the JVM just before terminating the 
	thread.

	class Test 									
	{											|		|    |		 |  |		|   |		|
		public static void main(String args[])  |-------|    |-------|  |-------|   |-------|
		{                                       |  m2() |    | 		 |  |  		|   |  		|
			m1();                               |-------|    |-------|  |-------|   |-------|
		}                                       |  m1   |    |  m1   |  |       |   |       |
		public static void m1()                 |-------|    |-------|  |-------|   |-------|
		{                                       |  Main |    |  Main |  |  Main |   |       |==> and it is destroyed by 
			m2();                               |-------|    |-------|  |-------|   |-------|	  JVM 	
		}                                                                           
		public static void m2()                 Runtime      Runtime    Runtime     Runtime 
		{                                        Stack        Stack      Stack       Stack 
		}
	}
	
 -> Each entry in the stack is called stack frame or Activation record.	
 
 -> The data stored in the stack is available only for the corrusponding thread and not available to the remaining threads 
	hence a these data is thread safe.
	
	
 	--------------------------------------------------------------------------
	|																		 |
	|	   t1 			 t2        t3           tn                           |
	|	|		|    |		 |  |		|   |		|                        |
	|   |-------|    |-------|  |-------|   |-------|                        |
	|   | 		|    | 		 |  |  		|   |  		|                        |
	|   |-------|    |-------|  |-------|   |-------|                        |
	|   |  	    |    |       |  |       |   |       |                        |
    |   |-------|    |-------|  |-------|   |-------|                        |
	|   |  		|    | 		 |  |       |   |     <-|-- Stack Frame          |
    |   |-------|    |-------|  |-------|   |-------|                        |
    |                                                                        |
    |   Runtime      Runtime    Runtime     Runtime                          |
    |    Stack        Stack      Stack       Stack                           |
    |                                                                        |
    |                                                                        |
    |                                                                        |
	--------------------------------------------------------------------------
								Stack Memory 
					
 ----------------------- 
  Stack Frame Structure 
 -----------------------

  -> Each stack frame containes three parts.


		--------------------------------
		|							   |
        | 		Local variable Array   |
        |                              |
        |------------------------------|
        |                              |
        |		 Operand Stack         |
        |                              |
        |------------------------------|
        |                              |
        | 		  Frame Data           |
        |                              |
        |------------------------------|
				Stack Frame 
				
				
				
	------------------------
	  Local variable Array
	------------------------

		-> It containes all parameters and locale variable of the method.
		
		-> Each slot in the array each of 4 bytes.
		
		-> Values of type int,float and reference occupied one entry in the array.
		
		-> Values of double and long occupied two consecutive entries in the array.
		
		-> byte, short and char values will be converted to int type before storing and occupied one slot.
		
		-> But the way of storing boolean values is different from JVM to JVM but most of the JVM follows one slot for 
		   boolean values.
			
			public void m1(int i, double d ,Object o, float f)
			{
				long x;
			}
			
		
		  int 	  double 	Object   float      long 
		 <-i-> <----d----> <--o-->	<--f--> <----x------>
		-------------------------------------------------
		|	  |  	|	 |        |		   |	 |		|
		|  i  | d   | d  |    o   |    f   |  x  |   x  |
		-------------------------------------------------
		   0	1	  2		 3 		   4       5     6



	----------------------
		Operand Stack
	----------------------	

		-> JVM uses Operand stack as work space.
		
		-> Some instructions can push  value to operand stack and some instructions can pop values from operand stack and 
		   and some instructions can perfome required operations.
		
		------------------------------------------------------------------------------------------------------------	
		|    program 	 |	  Before Starting 			|	After iload-0			  |	  After iload-1            |
		|  -----------   |                              |                             |                            |
		|  |iload-0  |   |    Local Variable -------	|	Local Variable	 -------  |   Local Variable  -------  |
		|  |iload-1  |   |    	  Array 	 | 100 |	|		Array       0| 100 |  |   	Array        0| 100 |  |
		|  |iadd 	 |	 |    				 -------    |                    -------  |                   -------  |
		|  |istore-2 |   |    				 |  90 |    |                   1|  90 |  |                  1|  90 |  |
		|  ----------    |    				 -------    |                    -------  |                   -------  |
		|                |    				 |	   |    |                   2|     |  |                  2|     |  |
		|                |    				 -------    |                    -------  |                   -------  |
		|				 |								|					 ------   |  				 ------    |
		|                |     				 ------     |     Operand Stack  |    |   |   Operand Stack  | 90 |    |
		|                |    Operand Stack  |    |     |    				 |-----   |  				 |-----    |
		|	             |    				 |-----     |    				 |100 |   |  				 |100 |    |
		|                |    				 |    |     |    				 ------   |  				 ------    |
		|                |    				 ------     |                             |                            |
		------------------------------------------------------------------------------------------------------------
		
		----------------------------------------------------------
		|After iadd 			  |		 After istore-2	         |
		|                         |                              |
		|Local Variable	 -------  |    Local Variable  -------   |
		|	Array       0| 100 |  |    	Array         0| 100 |   |
		|                -------  |                    -------   |
		|               1|  90 |  |                   1|  90 |   |
		|                -------  |                    -------   |
		|               2|     |  |                   2| 190 |   |
		|                -------  |                    -------   |
        |				 ------   |   				 ------      |
        | Operand Stack  |    |   |    Operand Stack |    |      |
        |				 |-----   |   				 |-----      |
        |				 |190 |   |   				 |    |      |
        |				 ------   |   				 ------      |
        |                         |                              |
		----------------------------------------------------------
		
    ---------------
	  Frame Data 
	---------------

		-> Frame data containes all symbolic references releted to that method.
		
		-> It also containes a reference to exception table which provides corrusponding catch block information in 
		   case of Exceptions.

		
==============================================
  4. PC Registers(Program Counter Registers).
==============================================

	-> For every thread a separate PC Registers will be created at the time of Thread creation.
	
	-> Pc Registers containes the address of current executing instructions.
	
	-> Once a instructions execution complites automatically PC Registers will be incremented to hold address of next 
	   instructions.
	   
============================
  5. Native Method Stacks	
============================

	-> For every thread JVM will create a separate Native Method Stack.
	
	-> All Native Method Calls invoked by the thread will be stored in the corrusponding Native method Stack.
	
		

 Note: 
 
	1. Method area, Heap area and Stack area are considard as important Memory areas with respect to programmer.
	
	2. Method are and Heap area are per JVM.
	
	3. where as Stack area PC Registers and Native method stack are per Thread.
    
	4. 
	
		For Every JVM |-----> One heap Area 
					  |-----> One Method Area 
					  
					  
		For Every Thread |-----> One stack area 
						 |-----> One PC Register 
						 |-----> One Native method Stack 
						 
						 

  -> Static variable will be stored in Method area.
  
  -> Instances variables will be stored in Heap Area.
  
  -> Local variable will be stored in Stack Area.
  
  
  
	class Test 
	{
		Student s1 = new Student();
		static Student s2  = new Student();
		
		public static void main(String args[])
		{
			Test t = new Test();
			
			Student s3 = new Student();
			
		}
	}
	
		-----------------				-----------------------------
		|				|               |						    |
		|  		s2----------------------->|--------- 	            |
		|               |               | | student|                |
		|               |               | |  Object|   ----------   |
		|               |               | ----------   | student|   |
		|               |               |              |  Object|   |
		|               |               |  ----------  ----------   |         
		-----------------           |---|--|--> s1--|------|        |
			Method Area 			|	|  |	    |               |
									|	|  | student|               |
                                    |   |  |  Object|               |
                                    |   |  ---^------               |
                                    |   |     |                     |
                |      |            |   |     |                     |
				|------|            |   ------|----------------------
				|  s3  |------------|		  |   Heap Area 
				|------|  					  |	
				|  t---|---------------------- 
				|------|
			   Stack Area 	







 
					