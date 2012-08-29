package controllers;


public class Security extends Secure.Security {
	
	static boolean check(String profile) {
//        User user = User.find("byEmail", connected());
//        return user.admin;
		return true;
    }    

}
