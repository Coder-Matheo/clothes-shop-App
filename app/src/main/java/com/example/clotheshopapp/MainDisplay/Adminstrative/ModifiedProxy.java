package com.example.clotheshopapp.MainDisplay.Adminstrative;

import com.example.clotheshopapp.MainDisplay.MainActivity;

import java.util.ArrayList;

/**
* Proxy Class:It will try to invoke the doSomeWork()
* of a ConcreteSubject instance
*/
class ModifiedProxy extends Subject{

	static Subject cs;
	String currentUser;
	ArrayList<String> registeredUsers;
	//Or, simply create this mutable list one step
	//List<Strig> registered users = new ArrayList<String>(Arrays.asList("Admin", "Rohit", "Sam"));
	
	public ModifiedProxy(String currentUser) {
		//Registered users are Admin, Rohit and Sam only.
		registeredUsers = new ArrayList<String>();
		registeredUsers.add("Admin");
		registeredUsers.add("Rohit");
		registeredUsers.add("Sam");
		this.currentUser = currentUser;
	}
	
	@Override
	public void doSomeWork(MainActivity mainActivity) {
		System.out.println("\n Proxy call happening now...");
		System.out.println(currentUser + " wants to invoke a Proxy method");
		
		if (registeredUsers.contains(currentUser)) {
			//Lazy initialization: We'll not instantiate until the method is called
			if (cs == null) {
				cs = new ConcreteSubject();
			}
			//Allow the registered user to invoke the method 
			cs.doSomeWork(mainActivity);
		}else {
			System.out.println("Sorry "+currentUser+ " , you do not have access rights.");
		}
		
	}

}
