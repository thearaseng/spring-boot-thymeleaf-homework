package com.kshrd.spring.controller.restful;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kshrd.spring.entity.User;
import com.kshrd.spring.entity.form.UserAddForm;
import com.kshrd.spring.entity.form.UserUpdateForm;
import com.kshrd.spring.response.HttpMessage;
import com.kshrd.spring.response.Pagination;
import com.kshrd.spring.response.ResponseHttpStatus;
import com.kshrd.spring.response.ResponseList;
import com.kshrd.spring.response.ResponseRecord;
import com.kshrd.spring.response.Table;
import com.kshrd.spring.response.Transaction;
import com.kshrd.spring.response.failure.ResponseListFailure;
import com.kshrd.spring.response.failure.ResponseRecordFailure;
import com.kshrd.spring.service.RoleService;
import com.kshrd.spring.service.UserService;

@RestController
@RequestMapping("/v1/api/user")
public class UserRestControllerV1 {

	@Autowired
	UserService userService;

	HttpStatus httpStatus = HttpStatus.OK;

	@GetMapping(value = "/find-all-user", headers = "Accept=application/json")
	public ResponseEntity<ResponseList<User>> findAllUser(
		@RequestParam(value = "page", required = false, defaultValue = "1") int page,
		@RequestParam(value = "limit", required = false, defaultValue = "20") int limit
	){
		ResponseList<User> responseList = new ResponseList<>();
		try{
			httpStatus = HttpStatus.OK;
			Pagination pagination = new Pagination();
			pagination.setLimit(limit);
			pagination.setPage(page);
			pagination.setTotalCount(userService.getUserCount());
			pagination.setTotalPages(pagination.getTotalPages());
			List<User> users = userService.findAllUser(pagination);
			if(!users.isEmpty()){
				responseList = new ResponseList<User>(HttpMessage.success(Table.USERS, Transaction.Success.RETRIEVE), true, users, pagination);
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
				responseList = new ResponseListFailure<>(HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE), false,
								ResponseHttpStatus.NOT_FOUND);
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
			responseList = new ResponseListFailure<>(HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE), false, 
							ResponseHttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ResponseList<User>>(responseList, httpStatus);
	}

	@GetMapping(value = "/{uuid}", headers = "Accept=application/json")
	public ResponseEntity<ResponseRecord<User>> findUserByUUID(@PathVariable("uuid") String uuid){
		ResponseRecord<User> responseRecord = new ResponseRecord<>();
		try{
			httpStatus = HttpStatus.OK;
			User user = userService.findUserByUUID(uuid);
			if(user!=null){
				responseRecord = new ResponseRecord<User>(HttpMessage.success(Table.USERS, Transaction.Success.RETRIEVE), true, user);
			}
			else{
				httpStatus = HttpStatus.NOT_FOUND;
				responseRecord = new ResponseRecordFailure<User>(HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE), false, ResponseHttpStatus.NOT_FOUND);
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
			responseRecord = new ResponseRecordFailure<User>(HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE), false, ResponseHttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ResponseRecord<User>>(responseRecord, httpStatus);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<ResponseRecord<User>> updateUser(@RequestBody UserUpdateForm userUpdateForm){
		ResponseRecord<User> responseRecord = null;
		try{
			httpStatus = HttpStatus.OK;
			if(userService.updateUser(userUpdateForm)){
				userService.deleteUserRoleByUserUuid(userUpdateForm.getUuid());
				userService.insertUserRole(userUpdateForm.getRoles(), userService.getUserIDByUUID(userUpdateForm.getUuid()));
				responseRecord = new ResponseRecord<>(HttpMessage.success(Table.USER_ROLES, Transaction.Success.UPDATED), true, userService.findUserByUUID(userUpdateForm.getUuid()));
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
				responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USERS, Transaction.Fail.UPDATED), false, ResponseHttpStatus.NOT_FOUND);
			}

		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USER_ROLES, Transaction.Fail.UPDATED),
														 true, ResponseHttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseRecord<User>>(responseRecord, httpStatus);
	}

	@PutMapping(value = "/status/{uuid}/{status}", headers = "Accept=application/json")
	public ResponseEntity<ResponseRecord<User>> updateUserStatusByUUID(@PathVariable("uuid") String uuid,
																	   @PathVariable("status") String status){
		ResponseRecord<User> responseRecord = null;
		try{
			httpStatus = HttpStatus.OK;
			if(userService.updateUserStatusByUUID(uuid, status)){
				responseRecord = new ResponseRecord<>(HttpMessage.success(Table.USER_ROLES, Transaction.Success.UPDATED), 
						 													true, userService.findUserByUUID(uuid));
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
				responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USERS, Transaction.Fail.UPDATED),  
																				false, ResponseHttpStatus.NOT_FOUND);
			}
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USER_ROLES, Transaction.Fail.UPDATED), 
														 					true, ResponseHttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseRecord<User>>(responseRecord, httpStatus);
	}

	@DeleteMapping(value = "/delete/{uuid}", headers = "Accept=application/json")
	public ResponseEntity<ResponseRecord<User>> deleteUserByUUID(@PathVariable("uuid") String uuid){
		ResponseRecord<User> responseRecord = null;
		try{
			httpStatus = HttpStatus.OK;
			User user = userService.findUserByUUID(uuid);
			userService.deleteUserRoleByUserUuid(user.getUuid());
			if(userService.deleteUserByUUID(uuid)){
				responseRecord = new ResponseRecord<>(HttpMessage.success(Table.USER_ROLES, Transaction.Success.DELETED),
																			true, user);
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
				responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USERS, Transaction.Fail.DELETED),  
																			false, ResponseHttpStatus.NOT_FOUND);
			}				
			
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USER_ROLES, Transaction.Fail.DELETED), 
														 				true, ResponseHttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseRecord<User>>(responseRecord, httpStatus);
	}	
	
	@PostMapping(value = "/insert", headers = "Accept=application/json")
	public ResponseEntity<ResponseRecord<User>> insertUser(@RequestBody UserAddForm userAddForm){
		ResponseRecord<User> responseRecord = null;
		try{
			httpStatus = HttpStatus.OK;
			if(userService.insertUser(userAddForm)){
				userService.insertUserRole(userAddForm.getRoles(), userService.getUserIDByUUID(userAddForm.getUuid()));
				responseRecord = new ResponseRecord<>(HttpMessage.success(Table.USER_ROLES, Transaction.Success.CREATED), 
						 												true, userService.findUserByUUID(userAddForm.getUuid()));
			}
			else{
				httpStatus = HttpStatus.NOT_FOUND;
				responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USERS, Transaction.Fail.CREATED),  
																				false, ResponseHttpStatus.NOT_FOUND);
			}				
			
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USER_ROLES, Transaction.Fail.CREATED), 
														 				true, ResponseHttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseRecord<User>>(responseRecord, httpStatus);
	}
	
	@PostMapping(value = "/find-user-by-email", headers = "Accept=application/json")
	public ResponseEntity<ResponseRecord<User>> findUserByEmail(@RequestParam("email") String email){
		ResponseRecord<User> responseRecord = null;
		try{
			httpStatus = HttpStatus.OK;
			User myUser = userService.findUserByEmail(email);
			if(myUser != null){
				responseRecord = new ResponseRecord<>(HttpMessage.success(Table.USER_ROLES, Transaction.Success.RETRIEVE), 
						 													true, myUser);
			}
			else{
				httpStatus = HttpStatus.NOT_FOUND;
				responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USERS, Transaction.Fail.RETRIEVE),  
																				false, ResponseHttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			responseRecord = new ResponseRecordFailure<>(HttpMessage.fail(Table.USER_ROLES, Transaction.Fail.RETRIEVE), 
														 				true, ResponseHttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ResponseRecord<User>>(responseRecord, httpStatus);
	}
	
}
