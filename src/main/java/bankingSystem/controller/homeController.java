package bankingSystem.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bankingSystem.dao.userRepository;
import bankingSystem.entity.user;

@Controller
public class homeController {

	@Autowired
	private userRepository userRepository;

	//home page
	@RequestMapping("/")
	public String homecontroller(Model m) {
		m.addAttribute("title", "Banking System");
		return "home";
	}

	//show customer details when pressing Transfer Money link
	@RequestMapping("/transfermoney")
	public String transfermoney(Model m, Principal principal) {
		m.addAttribute("title", "Show Customer");
		// String userName = principal.getName();
		List<user> user = this.userRepository.findAllUsers();
		m.addAttribute("user", user);
		System.out.println(user);
		return "transfermoney";
	}

	//money transfer form
	@PostMapping("/transfermoney/{id}")
	public String updateForm(@PathVariable("id") Integer id, Model m) {
		m.addAttribute("title", "Transfer Money");
		user user1 = this.userRepository.findById(id).get();
		m.addAttribute("user1",user1);
		System.out.println("Money Transfer:"+user1);
		return "moneytransferform";
	}
	
	//transfer money handler
	
	// update contact handler
		@PostMapping("/transactiondone")
		public String transferformHandler(@ModelAttribute user user,Model m, HttpSession session, Principal principal) {

			
				int balance = user.getBalance();
				System.out.println(balance); 
			
				//old money transfer details
				int oldbalance=this.userRepository.getBalance(user.getId());
				System.out.println(oldbalance); 
				int newbalance=balance+oldbalance;
				System.out.println(newbalance); 
				user.setBalance(newbalance);
				this.userRepository.save(user);
				m.addAttribute("title", "Transaction Done");
				return "transcationsuccesspage";
		}
}
