package com.hometask.payments.revolut_payments;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.h2.jdbc.JdbcConnection;
import org.h2.jdbcx.JdbcDataSource;

import com.hometask.payments.Model.Amount;
import com.hometask.payments.Model.Transfer;;
import com.hometask.payments.Model.User;
import com.hometask.payments.hibernate.service.UserService;

@Path("/payments")
public class PaymentsRest {

	
	
		@POST
		@Path("/register")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response createTrackInJSON(User user) {

			String result = "Track saved : " + user;
			try {
			
			UserService userService = new UserService();
			userService.persist(user);
			
			
			}
			catch(Exception e) {
				return Response.status(201).entity("User already exists").build();
			}
			
			return Response.status(201).entity(result).build();
		}
		
		@GET
		@Path("/existingusers")
		@Produces(MediaType.APPLICATION_JSON)
		public List<User> existingUsers(){
			UserService userService = new UserService();
			return userService.findAll();
		}
		
		@GET
		@Path("/accountdetails/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getUserDetails(@PathParam("id") String id){
			UserService userService = new UserService();
			String result = "";
			if(userService.findById(id)==null)
				result+="Account not found.";
			else
				result+=userService.findById(id);
			
			
			
			return Response.status(201).entity(result).build();
		}
		
		@POST
		@Path("/transfer")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response transfer(Transfer transfer) {
			UserService userService = new UserService();
			
			
			String result = "";
			String fromAcc = transfer.getFromAcc();
			String toAcc = transfer.getToAcc();
			int amount = transfer.getAmount();
			User fromUser = userService.findById(fromAcc);
			User toUser = userService.findById(toAcc);
			if(amount>fromUser.getBalance())
				result+="Insufficient funds in the account. Amount cannot be transferred. Available balance in the account is:"+fromUser.getBalance();
			else
			{
				fromUser.debit(amount); //Debit amount from the debit account	
				toUser.credit(amount);	//Credit to the toAccount
				userService.update(fromUser);
				userService.update(toUser);
				result+="Amount transferred to Account: "+toUser.getName()+". Available balance is in the account is: "+fromUser.getBalance();
		
			}
			return Response.status(201).entity(result).build();
		}
		
		@POST
		@Path("/credit/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response credit(@PathParam("id") String id,Amount amount ) {
			String result = "";
			UserService userService = new UserService();
			User creditUser = userService.findById(id);
			creditUser.credit(amount.getAmount());
			userService.update(creditUser);
			result+="Amount credited into " + id+" Account";
			return Response.status(201).entity(result).build();
		}
		
		@POST
		@Path("/debit/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response debit(@PathParam("id") String id,Amount amount ) {
			String result = "";
			UserService userService = new UserService();
			User debitUser = userService.findById(id);
			if("FAILURE".equalsIgnoreCase(debitUser.debit(amount.getAmount())))
				result+="Insufficient funds in the account. Available balance in the account is: "+debitUser.getBalance();
			else {
				userService.update(debitUser);
				result+="Amount debited from into " + id + " Account. Available balance in the account is: "+debitUser.getBalance();
			}
				
			
			return Response.status(201).entity(result).build();
		}
		
		@POST
		@Path("/delete/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteUser(@PathParam("id") String id){
			UserService userService = new UserService();
			userService.delete(id);
			String result = "Account deleted";
			return Response.status(201).entity(result).build();
		}
		
		@POST
		@Path("/deleteall")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteAll(){
			UserService userService = new UserService();
			userService.deleteAll();
			String result = "All Accounts deleted";
			return Response.status(201).entity(result).build();
		}
}
