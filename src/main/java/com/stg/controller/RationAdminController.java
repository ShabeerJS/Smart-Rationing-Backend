package com.stg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Address;
import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.OrderStatus;
import com.stg.entity.Product;
import com.stg.entity.RationAdmin;
import com.stg.entity.RationCardMember;
import com.stg.entity.RationCard;
import com.stg.exception.RationApplicationException;
import com.stg.service.RationAdminService;

@RestController
@RequestMapping(value = "/rationAdmin")
@CrossOrigin(origins  = "*")
public class RationAdminController {

	@Autowired
	private RationAdminService rationAdminService;
	
	@GetMapping(value = "adminLogin/{emailId}")
	public RationAdmin rationAdminLogin(@PathVariable String emailId) {
		return rationAdminService.rationAdminLogin(emailId);
		
	}

	@PostMapping(value = "/addRationAdmin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RationAdmin> addRationAdmin(@RequestBody RationAdmin rationAdmin)
			throws RationApplicationException {
		return new ResponseEntity<RationAdmin>(rationAdminService.addRationAdmin(rationAdmin), HttpStatus.CREATED);

	}

//	@GetMapping(value = "/readAllRationAdmin")
//	public ResponseEntity<List<RationAdmin>> readAllRationAdmin() throws RationApplicationException {
//
//		return new ResponseEntity<List<RationAdmin>>(rationAdminService.readAllRationAdmin(), HttpStatus.OK);
//	}

	// @GetMapping(value = "/readRationAdmin/{rationAdminId}")
	public ResponseEntity<RationAdmin> readRationAdmin(@PathVariable int rationAdminId)
			throws RationApplicationException {
		return new ResponseEntity<RationAdmin>(rationAdminService.readRationAdmin(rationAdminId), HttpStatus.OK);
	}

//	@PutMapping(value = "/updateRationAdminPassword/")
//	public ResponseEntity<String> updatePassword(@RequestParam int rationAdminId, @RequestParam String password)
//			throws RationApplicationException {
//
//		return new ResponseEntity<String>(rationAdminService.updatePassword(rationAdminId, password),
//				HttpStatus.ACCEPTED);
//	}

	// @DeleteMapping(value = "/deleteRationAdmin/{rationAdminId}")
	public ResponseEntity<String> deleteRationAdmin(@PathVariable int rationAdminId) throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.deleteRationAdmin(rationAdminId), HttpStatus.OK);

	}

	// Order========================================= //
	@GetMapping(value = "/readAllOrders")
	public ResponseEntity<List<OrderDetails>> readOrders()
			throws RationApplicationException {
		return new ResponseEntity<List<OrderDetails>>(rationAdminService.readOrders(), HttpStatus.OK);

	}
	
	@GetMapping(value = "/readbyOrderId/{orderId}")
	public ResponseEntity<OrderDetails> readOrders(@PathVariable int orderId) throws RationApplicationException {
		return new ResponseEntity<OrderDetails>(rationAdminService.readOrders(orderId), HttpStatus.OK);
		
	}
	@PutMapping(value = "/UpdateOrder/{orderId}/{orderStatus}")
	public ResponseEntity<String> UpdateOrderStatus(@PathVariable int  orderId,
			@PathVariable OrderStatus orderStatus) throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.UpdateOrderStatus(orderId, orderStatus),
				HttpStatus.OK);
	}


	// RationCard User
//	@GetMapping(value = "/readRationCardUsers")
//	public ResponseEntity<List<RationCard>> readUsers(@RequestParam int rationAdminId)
//			throws RationApplicationException {
//		return new ResponseEntity<List<RationCard>>(rationAdminService.readUsers(rationAdminId), HttpStatus.OK);
//	}

	//================Products=======================================
	@GetMapping(value = "/readAllProducts")
	public ResponseEntity<List<Product>> readProduct()
			throws RationApplicationException {
		return new ResponseEntity<List<Product>>(rationAdminService.readProduct(), HttpStatus.OK);

	}
  
	@GetMapping(value ="/readByIdProduct/{productId}")
	public ResponseEntity<Product> readByIdProducts(@PathVariable int productId) throws RationApplicationException{
		return new ResponseEntity<Product>(rationAdminService.readByIdProducts(productId), HttpStatus.OK);
	}
	
	@PutMapping(value = "updateProduct/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable int productId,@RequestBody Product product){
		return new ResponseEntity<Product>(rationAdminService.updateProduct(productId, product), HttpStatus.OK);
	}
	

	
		@PostMapping(value = "/addProducts/{rationAdminId}")
		public ResponseEntity<Product> addProducts(@RequestBody Product products,@PathVariable int rationAdminId)
				throws RationApplicationException {
			return new ResponseEntity<Product>(rationAdminService.addProducts(products, rationAdminId), HttpStatus.OK);
		}

		@DeleteMapping(value = "/deleteProducts/{productId}")
		public ResponseEntity<String> deleteProduct(@PathVariable int productId)
				throws RationApplicationException {
			return new ResponseEntity<String>(rationAdminService.deleteProduct(productId), HttpStatus.OK);

		}
	// RationCardMember
	@DeleteMapping(value = "/deleteRationCardMember")
	public ResponseEntity<String> deleteRationCardMember(@RequestParam int rationAdminId,
			@RequestParam long rationCardNo, @RequestParam int memberId) throws RationApplicationException {
		return new ResponseEntity<String>(
				rationAdminService.deleteRationCardMember(rationAdminId, rationCardNo, memberId), HttpStatus.OK);

	}

//	@PostMapping(value = "/addRationCardMember")
//	public ResponseEntity<RationCardMember> addRationCardMember(@RequestBody RationCardMember rationCardMember,
//			@RequestParam long rationCardNo) throws RationApplicationException {
//		return new ResponseEntity<RationCardMember>(
//				rationAdminService.addRationCardMember(rationCardMember, rationCardNo), HttpStatus.CREATED);
//
//	}
	
	@PostMapping(value = "/addRationCardMember/{rationId}")
	public ResponseEntity<RationCardMember> addRationCardMember(@RequestBody RationCardMember rationCardMember,
	@PathVariable int rationId	) throws RationApplicationException {
		return new ResponseEntity<RationCardMember>(
				rationAdminService.addRationCardMember(rationCardMember,rationId), HttpStatus.CREATED);

	}
//==============================RationCard===============================
	// RationCard
	@GetMapping(value = "/readAllRationCard")
	public ResponseEntity<List<RationCard>> readRationCard()
			throws RationApplicationException {
		return new ResponseEntity<>(rationAdminService.readRationCard(), HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/rationCardUsers/{rationCardNo}")
	public ResponseEntity<RationCard> readRationCard(@PathVariable long rationCardNo) throws RationApplicationException {
		RationCard card = rationAdminService.readRationCard(rationCardNo);
		card.setPassword("****");
		// card.setFixedProduct(null);
		card.setOrders(null);
		return new ResponseEntity<RationCard>(card, HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteRationCard/{rationCardNo}")
	public ResponseEntity<String> deleteRationCard( @PathVariable long rationCardNo)
			throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.deleteRationCard(rationCardNo),
				HttpStatus.OK);
	}

	@PostMapping(value = "/addRationCard/{rationAdminId}")
	public ResponseEntity<RationCard> addRationCard(@RequestBody RationCard rationCard,@PathVariable int rationAdminId)
			throws RationApplicationException {
		// RationCard rationCard2=rationAdminService.addRationCard(rationCard,
		// rationAdminId);

		return new ResponseEntity<RationCard>(rationAdminService.addRationCard(rationCard, rationAdminId),
				HttpStatus.OK);

	}
//==============================================================================================================

	// @GetMapping(value = "/searchingArea")
	public ResponseEntity<List<Address>> searchingArea(@RequestParam int rationAdminId, @RequestParam String area)
			throws RationApplicationException {

		return new ResponseEntity<List<Address>>(rationAdminService.searchingArea(rationAdminId, area), HttpStatus.OK);

	}

	// @PutMapping(value = "/SetAdminId")
	public ResponseEntity<String> UpdateAdminIdToRationCard(@RequestParam int AdminId, @RequestParam int rationId)
			throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.UpdateAdminIdToRationCard(AdminId, rationId),
				HttpStatus.ACCEPTED);
	}

	
	 //==========================================FixedProducts====================
	@PostMapping(value = "/addFixedProducts/{rationAdminId}")
	public ResponseEntity<FixedProducts> addFixedProducts(@RequestBody FixedProducts fixedProducts,
			@PathVariable int rationAdminId	) throws RationApplicationException {
		return new ResponseEntity<FixedProducts>(rationAdminService.addFixedProducts(fixedProducts, rationAdminId),
				HttpStatus.OK);
	}
	
	@GetMapping(value = "/readByIdFixedProduct/{productId}")
	public ResponseEntity<FixedProducts> readByFixedProductId(@PathVariable int productId)throws RationApplicationException{
		return new ResponseEntity<FixedProducts>(rationAdminService.readByIdFixedProducts(productId), HttpStatus.OK);
	}
	@GetMapping(value = "/readAllFixedProducts")
	public List<FixedProducts> readFixedProduct() throws RationApplicationException {
		return rationAdminService.readFixedProduct();
	}

	@PutMapping(value = "/updateFixedProducts/{productId}")
	public ResponseEntity<FixedProducts> updateFixedProduct(
			@PathVariable int productId,@RequestBody FixedProducts fixedProducts) throws RationApplicationException {
		return new ResponseEntity<FixedProducts>(
				rationAdminService.updateFixedProduct(productId, fixedProducts),HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteFixedProducts/{productId}")
	public ResponseEntity<String> deleteFixedProduct(@PathVariable int productId)
			throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.deleteFixedProduct(productId),
				HttpStatus.OK);

	}
//===============================================================================================
//	@PostMapping(value = "/create")
//	public ResponseEntity<Address> addAddress(@RequestBody Address address, @RequestParam int rationId)
//			throws RationApplicationException {
//		return new ResponseEntity<Address>(rationAdminService.addAddress(address, rationId), HttpStatus.CREATED);
//	}

	

	

//=================================================================================
	@GetMapping(value = "AllOrdersByMonth")
	public ResponseEntity<List<OrderDetails>> readOrderBymonth(@RequestParam int month)
			throws RationApplicationException {
		return new ResponseEntity<List<OrderDetails>>(rationAdminService.readOrderBymonth(month), HttpStatus.OK);
	}

	// =================address=======================
	@PostMapping(value = "addAddress/{rationId}")
	public ResponseEntity<Address> addAddress(@RequestBody Address address,@PathVariable int rationId)
			throws RationApplicationException {
		return new ResponseEntity<Address>(rationAdminService.addAddress(address,rationId), HttpStatus.OK);
	}

	// =====================================Add List Of
	// rationCardMembers========================
	@PostMapping(value = "ListOfMembers")
	public ResponseEntity<List<RationCardMember>> addListRationCardMember(
			@RequestBody List<RationCardMember> rationCardMember, @RequestParam long rationCardNo)
			throws RationApplicationException {

		return new ResponseEntity<List<RationCardMember>>(
				rationAdminService.addListRationCardMember(rationCardMember, rationCardNo), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "ListOfMembers/{rationId}")
	public ResponseEntity<List<RationCardMember>> addListRationCardMember(
			@RequestBody List<RationCardMember> rationCardMember, @PathVariable int rationId)
			throws RationApplicationException {

		return new ResponseEntity<List<RationCardMember>>(
				rationAdminService.addListRationCardMember(rationCardMember, rationId), HttpStatus.CREATED);
	}
}
