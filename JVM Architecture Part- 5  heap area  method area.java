
											JVM Architecture Part- 5|| heap area || method area
										==========================================================


-----------------
  2. Heap Area.	
-----------------

 -> For every JVM one heap area is available.
 
 -> Head will be created at the time of JVM startup.
 
 -> Objects and corrusponding instance variable will be stored in the heap area.
 
 -> Every Array in java is object only hence a arrays also we will be stored in the heap area.
 
 -> Heap area can be accessed by multiple thread and hence a the data stored in the heap memory is not thread safe.
 
 -> Heap area need not be continues 

						
							-------------------------------
                            |             				  |
                            |   -------   ------- ------- |
                            |	|Object|  |Object||Object||
                            |	|level |  |level ||level ||
                            |	|data  |  |data  ||data  ||
                            |	-------	  ------- ------- |
                            |							  |
                            |	-------	 ------- -------  |
                            |	|Object| |Object||Object| |
                            |	|level | |level ||level | |
                            |	|data  | |data  ||data  | |
                            |	-------	 ------- -------  |
                            |							  |
                            |              			      |
                            ------------------------------	
									Heap Area
									
	
  Q. Program to display heap memory stastics ?
  
		- A java application can communicate with JVM by using Runtime object.
		
		- Runtime class prasent in java.lang package and it is Singleton class.
		
		- We can create Runtime object as follows:
		
				Runtime r = Runtime.getRuntime();
		
		- Once we got Runtime object we can call the following methods on that object.
		
			1. maxMemory();
					
				- It returns the number of bytes of  maxMemory memory allocated to the heap.
				 
			2. totalMemory();
			
				- It returns number of bytes of totalMemory allocated to the heap(initialMemory).

			3. freeMemory();
			
				- It returns number of bytes freeMemory prasent in the heap.
				
						consumeMemory = initialMemory()-freeMemory();
			
        
							 -----------------			     -------
                             | 				|				 | . . |
                             | 				|				 |  -  |
                             |				|				 -------
                             |				|					|
                             |				|                   |
                             |    java		|                   |
                             |	Application |-----------------> ^
                             |				|                   |
                             |				|                   |
                             |				|                   |
                             |				|                   |
                             |				|                   |
                             |				|                   ^
		                     | 				|                   |
                             -----------------                   |
                                                                JVM  
       
       
       class HeapDemo
	   {
		   public static void main(String args[])
		   {
			   Runtime r = Runtime.getRuntime();
			   System.out.println("Max Memory :"+r.maxMemory());
			   System.out.println("Total Memory :"+r.totalMemory());
			   System.out.println("Free  Memory :"+r.freeMemory());
			   System.out.println("Consume Memory :"+(r.totalMemory()-r.freeMemory()));
		   }
	   }
	   output int byte: 
	   
	   Max Memory :1046478848
	   Total Memory :67108864
	   Free  Memory :65240104
	   Consume Memory :1868760
	   
	   
	   class HeapDemo
	   {
		   public static void main(String args[])
		   {
			   long mb = 1024*1024;
			   Runtime r = Runtime.getRuntime();
			   System.out.println("Max Memory :"+r.maxMemory()/mb);
			   System.out.println("Total Memory :"+r.totalMemory()/mb);
			   System.out.println("Free  Memory :"+r.freeMemory()/mb);
			   System.out.println("Consume Memory :"+(r.totalMemory()-r.freeMemory())/mb);
		   }
	   }
       output int MB :
	   Max Memory :998
	   Total Memory :64
	   Free  Memory :62
	   Consume Memory :1
	   
 -> How to set Maximum and Minimum heap sizes ?

	 - Heap Memory finite memory but based on our requirement we can set Maximum and Minimum heap sizes that is we can 	
	   increase and decrease the heap size based on our requirement.
	   
	 - We can use the following flags with java command.
	 
			1. -Xmx  :
				
				- To set Maximum heap size(maxMemory())
			
					Ex. 
						java -Xmx512m HeapDemo
					
						- This command will set Maximum heap as 512mb.
						
						
			
			2. -Xms :
			
				- We can use this command to set minimum heap size.
				
					Ex. 
			
					   java -Xms128m HeapDemo
					   
					   - To set minimum heap as 128mb that is totalMemory().
					   
	EX. 

		java -Xmx512m -Xms128m HeapDemo
		
			Max Memory :512.0
		    Total Memory :128.0
		    Free  Memory :126.0
		    Consume Memory :2.0