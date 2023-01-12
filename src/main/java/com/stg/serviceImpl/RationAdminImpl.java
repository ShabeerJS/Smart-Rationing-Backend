package com.stg.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.stg.entity.Address;
import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.OrderStatus;
import com.stg.entity.Product;
import com.stg.entity.RationAdmin;
import com.stg.entity.RationCard;
import com.stg.entity.RationCardMember;
//import com.stg.entity.RationCardUser;
import com.stg.exception.RationApplicationException;
import com.stg.repository.AddressRepository;
import com.stg.repository.FixedProductRepository;
import com.stg.repository.OrderRepository;
import com.stg.repository.ProductRepository;
import com.stg.repository.RationAdminRepository;
import com.stg.repository.RationCardMemberRepository;
import com.stg.repository.RationCardRepository;
import com.stg.service.RationAdminService;

@Service
public class RationAdminImpl implements RationAdminService {

	@Autowired
	private RationAdminRepository rationAdminRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private FixedProductRepository fixedProductRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private RationCardRepository rationCardRepository;
	@Autowired
	private RationCardMemberRepository rationCardMemberRepository;

	// Ration Admin/

	@Override
	public RationAdmin rationAdminLogin(String emailId) {
		String messege = "";
		RationAdmin admin = null;
		RationAdmin rationAdminGet = rationAdminRepository.findByEmailId(emailId);
		if (rationAdminGet.getEmailId().equals(emailId)) {
			admin = rationAdminGet;
		} else {
			throw new RationApplicationException("You have entered Wrong Mail Id");
		}
		return admin;
	}

	@Override
	public RationAdmin addRationAdmin(RationAdmin rationAdmin) throws RationApplicationException {
//		if (!(String.valueOf(rationAdmin.getRationAdminId()).length() > 0)) {
//			throw new RationApplicationException("Enter the Number");
//		}
//		if (!rationAdmin.getRationAdminName().matches("[a-zA-Z0-9]+")) {
//			throw new RationApplicationException("Enter the Name without SpecialCharacter");
//		}
//		if (!rationAdmin.getPassword().matches("[a-zA-Z0-9-@#]{6,8}")) {
//			throw new RationApplicationException("Enter the Correct pattern pasword");
//		}
		if (rationAdmin != null) {
			return rationAdminRepository.save(rationAdmin);
		} else {
			throw new RationApplicationException("RationAdmin is Null");
		}

	}

//	@Override
//	public List<RationAdmin> readAllRationAdmin() throws RationApplicationException {
//
//		List<RationAdmin> rationAdmins = rationAdminRepository.findAll();
//
//		if (rationAdmins != null) {
//			return rationAdmins;
//		} else {
//			throw new RationApplicationException("RationAdmin is Null ");
//		}
//
//	}

	@Override
	public RationAdmin readRationAdmin(int rationAdminId) throws RationApplicationException {
		if (String.valueOf(rationAdminId).length() > 0) {
			RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
			return rationAdmin;
		} else {
			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
		}
	}

//	@Override
//	public String updatePassword(int rationAdminId, String password) throws RationApplicationException {
//		RationAdmin rationAdmin = null;
//		if (rationAdminRepository.existsById(rationAdminId)) {
//			Optional<RationAdmin> optional = rationAdminRepository.findById(rationAdminId);
//			optional.get().setPassword(password);
//			rationAdmin = optional.get();
//			rationAdminRepository.save(rationAdmin);
//			return "Password Updated Successfully";
//		} else {
//			throw new RationApplicationException("Unable to Update");
//		}
//	}

	@Override
	public String deleteRationAdmin(int rationAdminId) throws RationApplicationException {
		if (rationAdminRepository.existsById(rationAdminId)) {
			rationAdminRepository.deleteById(rationAdminId);
			return "rationAdminId" + rationAdminId + " Deleted Successfully";
		} else {
			throw new RationApplicationException("Unable to delete");
		}
	}

	// RationAdmin----Order//
	@Override
	public List<OrderDetails> readOrders() throws RationApplicationException {

//		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
//		if (rationAdminRepository.existsById(rationAdminId)) {

		return orderRepository.findAll();

//		} else {
//			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
//		}

	}

	@Override
	public OrderDetails readOrders(int orderId) throws RationApplicationException {

		OrderDetails orderDetails = orderRepository.findById(orderId).get();
		return orderDetails;
	}

	// =============================================================================
	// RationAdmin---------RationCardUsers//
	@Override
	public List<RationCard> readUsers(int rationAdminId) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		if (rationAdminRepository.existsById(rationAdminId)) {

			return rationAdmin.getRationCards();
		}

		else {
			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
		}

	}

	// RationAdmin---------------Product//
	@Override
	public List<Product> readProduct() throws RationApplicationException {
//		List<Product> list = new ArrayList<Product>();
//		if (rationAdminRepository.existsById(rationAdminId)) {
//			List<Product> listAll = productRepository.findAll();
//			for (Product product : listAll) {
//				if (product.getRationAdmin().getRationAdminId() == rationAdminId) {
//					list.add(product);
//				}
//			}
//		}
		return productRepository.findAll();

	}

	@Override
	public Product readByIdProducts(int productId) throws RationApplicationException {
		Product product = productRepository.findById(productId).get();
		return product;
	}

	@Override
	public Product updateProduct(int productId, Product product) throws RationApplicationException {

		Product product2 = productRepository.findById(productId)
				.orElseThrow(() -> new RationApplicationException("Not Found"));
		product2.setProductName(product.getProductName());
		product2.setProductPrice(product.getProductPrice());
		product2.setQuantity(product.getQuantity());

		Product updatedproduct = productRepository.save(product2);
		return updatedproduct;
	}

	@Override
	public Product addProducts(Product products, int rationAdminId) throws RationApplicationException {
//		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
//		products.setRationAdmin(rationAdmin);

		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		products.setRationAdmin(rationAdmin);

		return productRepository.save(products);
	}

	@Override
	public String deleteProduct(int productId) throws RationApplicationException {

		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			return "\"Deleted successfully\"";

		} else {
			throw new RationApplicationException("product Id is incorrect");
		}
	}
	// ========================================================================

//	@Override
//	public Product updateProduct(int rationAdminId, int productId, int quantity) throws RationApplicationException {
//
//		if (rationAdminRepository.existsById(rationAdminId)) {
//			if (productRepository.existsById(productId)) {
//				Product product = productRepository.findById(productId).get();
//				product.setQuantity(quantity);
//				return productRepository.save(product);
//			} else {
//				throw new RationApplicationException(productId + " Given product Id is Not Found");
//			}
//		} else {
//			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
//		}
//
//	}

	// RationAdmin------RationCardMember//
	@Override
	public String deleteRationCardMember(int rationAdminId, long rationCardNo, int memberId)
			throws RationApplicationException {
		if (rationAdminRepository.existsById(rationAdminId)) {
			RationCard rationCardUser = rationCardRepository.findByRationCardNo(rationCardNo);
			if (rationCardUser.getRationCardNo() == rationCardNo) {
				rationCardMemberRepository.deleteById(memberId);
				return "RationCardMember Deleted successfully";
			} else {
				throw new RationApplicationException(rationCardNo + "You Have Entered Wrong RationCard No");
			}
		} else {
			throw new RationApplicationException(rationAdminId + " Given product Id is Not Found");
		}

	}

//	@Override
//	public RationCardMember addRationCardMember(RationCardMember rationCardMember, long rationCardNo)
//			throws RationApplicationException {
//		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNo);
//		if (rationCardMember != null) {
//			rationCardMember.setRationCard(rationCard);
//			return rationCardMemberRepository.save(rationCardMember);
//		} else {
//			throw new RationApplicationException("can't able to create");
//		}
//	}

	@Override
	public RationCardMember addRationCardMember(RationCardMember rationCardMember, int rationId)
			throws RationApplicationException {
		RationCard rationCard = rationCardRepository.findById(rationId).get();
		rationCardMember.setRationCard(rationCard);

		return rationCardMemberRepository.save(rationCardMember);
	}

	// Ration Card User====================================
	@Override
	public List<RationCard> readRationCard() throws RationApplicationException {
//		List<RationCard> list = new ArrayList<RationCard>();
//
//		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
//		if (rationAdminRepository.existsById(rationAdminId)) {
//
//			List<RationCard> listAll = rationCardRepository.findAll();
//			for (RationCard rationCard : listAll) {
//				if (rationCard.getRationAdmin().getRationAdminId() == rationAdminId) {
//					list.add(rationCard);
//				}
//			}
//		} else {
//			throw new RationApplicationException(rationAdminId + "You Have Entered Wrong  rationAdminId ");
//		}
		return rationCardRepository.findAll();
	}

	@Override
	public RationCard readRationCard(long rationCardNo) throws RationApplicationException {
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNo);
		return rationCard;
	}

	@Override
	public RationCard addRationCard(RationCard rationCard, int rationAdminId) throws RationApplicationException {

		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		rationCard.setRationAdmin(rationAdmin);

		return rationCardRepository.save(rationCard);
//		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
//		List<FixedProducts> fixedProducts=fixedProductRepository.findAll();
//		rationCard.setRationAdmin(rationAdmin);
//		List<FixedProducts> fixedProducts3=new ArrayList<>();
//		
//		for (FixedProducts fixedProducts2 : fixedProducts) {
//			if (rationCard.getType().equals(fixedProducts2.getCardType())) {
//
//				fixedProducts3.add(fixedProducts2);
//			}
//		}
//		rationCard.setFixedProduct(fixedProducts3);
//		return rationCardRepository.save(rationCard);
//	
	}

//	
//			Address address = rationCard.getAddresses();
//			if (address.getArea().equalsIgnoreCase(area)) {

	@Override
	public String deleteRationCard(long rationCardNo) throws RationApplicationException {
//		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
//		if (rationAdminRepository.existsById(rationAdminId)) {

		RationCard rationCardUser = rationCardRepository.findByRationCardNo(rationCardNo);
		if (rationCardUser.getRationCardNo() == rationCardNo) {
			rationCardRepository.delete(rationCardUser);
			return "\"RationCard Deleted successfully\"";

		} else {
			throw new RationApplicationException(rationCardNo + "You Have Entered Wrong RationCard No");
		}
	}

//		else {
//
//			throw new RationApplicationException(rationAdminId + " Given rationAdmin Id is Not Found");
//		}

//=================================================================================
	@Override
	public List<Address> searchingArea(int rationAdminId, String area) throws RationApplicationException {

		return addressRepository.findAllByArea(area);

	}

	@Override
	public String UpdateOrderStatus(int orderId, OrderStatus orderStatus) throws RationApplicationException {
		OrderDetails orderDetails1 = orderRepository.findById(orderId).get();
		{
			orderDetails1.setOrderStatus(orderStatus);
			orderRepository.save(orderDetails1);

			return "\"Order Status Updated Successfully\"";
		}

	}

	@Override
	public String UpdateAdminIdToRationCard(int AdminId, int rationId) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(AdminId).get();
		RationCard rationCard = rationCardRepository.findById(rationId).get();
		rationCard.setRationAdmin(rationAdmin);
		rationCardRepository.save(rationCard);
		if (rationAdmin != null) {
			return "Updated Successfully";
		} else {
			throw new RationApplicationException("Admin Id is incorrect");
		}
	}

	// ==========================Products=============================

	// ====================================FixedProducts=================
	@Override
	public FixedProducts addFixedProducts(FixedProducts fixedProducts, int rationAdminId)
			throws RationApplicationException {
//		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
//		//List<RationCard> rationCards = rationCardRepository.findAll();
//		if (rationAdminRepository.existsById(rationAdminId)) {
//			fixedProducts.setRationAdmin(rationAdmin);
//			List<RationCard> list=new ArrayList<>();
//			
//			for (RationCard rationCard : rationCards) {
//				if (rationCard.getType().equals(fixedProducts.getCardType())) {
//
//					list.add(rationCard);
//				}
//			}
//			fixedProducts.setCards(list);
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		fixedProducts.setRationAdmin(rationAdmin);

		return fixedProductRepository.save(fixedProducts);
	}
//		} else {
//			throw new RationApplicationException("Admin Id is incorrect");
//		}
//	}

	@Override
	public List<FixedProducts> readFixedProduct() throws RationApplicationException {
//		List<FixedProducts> list = new ArrayList<FixedProducts>();
//		if (rationAdminRepository.existsById(rationAdminId)) {
//			List<FixedProducts> fixedProducts = fixedProductRepository.findAll();
//			for (FixedProducts fixedProduct : fixedProducts) {
//				if (fixedProduct.getRationAdmin().getRationAdminId() == rationAdminId) {
//					list.add(fixedProduct);
//				}
//			}
//		}

//		    if(fixedProducts.isEmpty()) {
//		    	throw new RationApplicationException("empty");
//		    }
		return fixedProductRepository.findAll();
	}

	@Override
	public FixedProducts updateFixedProduct(int productId, FixedProducts fixedProducts) {
		FixedProducts fixedProductsNew = fixedProductRepository.findById(productId)
				.orElseThrow(() -> new RationApplicationException("Not Found"));
		fixedProductsNew.setProductName(fixedProducts.getProductName());
		fixedProductsNew.setProductPrice(fixedProducts.getProductPrice());
		fixedProductsNew.setProductQuantity(fixedProducts.getProductQuantity());
		fixedProductsNew.setCardType(fixedProducts.getCardType());

		FixedProducts updatedFixedproduct = fixedProductRepository.save(fixedProductsNew);
		return updatedFixedproduct;
	}

	@Override
	@Cascade(CascadeType.DELETE)
	public String deleteFixedProduct(int productId) throws RationApplicationException {
		if (fixedProductRepository.existsById(productId)) {
			fixedProductRepository.deleteById(productId);
			return "\"Deleted successfully\"";

		} else {
			throw new RationApplicationException("product Id is incorrect");
		}
	}

	@Override
	public FixedProducts readByIdFixedProducts(int productId) throws RationApplicationException {
		FixedProducts fixedProducts = fixedProductRepository.findById(productId).get();
		return fixedProducts;

	}
//================================================================================

	@Override
	public Address addAddress(Address address, int rationId) throws RationApplicationException {
		RationCard rationCard2 = rationCardRepository.findById(rationId).get();

//			rationCard2.setAddresses(address);
		address.setRationCard(rationCard2);
//			rationCardRepository.save(rationCard2);

		return addressRepository.save(address);
	}

//		} else {
//			throw new RationApplicationException("New address Can't able to create");
//		}
//	}

	@Override
	public List<OrderDetails> readOrderBymonth(int month) throws RationApplicationException {
		List<OrderDetails> list = orderRepository.findAll();
		List<OrderDetails> list2 = new ArrayList<OrderDetails>();
		if (month > 0 && month <= 12) {
			for (OrderDetails orderDetails : list) {
				if (orderDetails.getLocalDate().getMonthValue() == month) {
					list2.add(orderDetails);
				}
			}

		} else {
			throw new RationApplicationException("Enter the Correct Month Value");
		}
		return list2;

	}

	@Override
	public List<RationCardMember> addListRationCardMember(List<RationCardMember> rationCardMember, long rationCardNo)
			throws RationApplicationException {

		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNo);
		List<RationCardMember> rationCardMembers = new ArrayList<RationCardMember>();

		for (RationCardMember rationCardMember2 : rationCardMember) {
			rationCardMember2.setRationCard(rationCard);
		}
		if (rationCardMember != null) {
			// rationCardMember2.setRationCard(rationCard);
			return rationCardMemberRepository.saveAll(rationCardMember);
		} else {
			throw new RationApplicationException("can't able to create");
		}

	}

	@Override
	public List<RationCardMember> addListRationCardMember(List<RationCardMember> rationCardMember, int rationId)
			throws RationApplicationException {

		RationCard rationCard = rationCardRepository.findById(rationId).get();
		List<RationCardMember> rationCardMembers = new ArrayList<RationCardMember>();

		for (RationCardMember rationCardMember2 : rationCardMember) {
			rationCardMember2.setRationCard(rationCard);
		}
		if (rationCardMember != null) {
			// rationCardMember2.setRationCard(rationCard);
			return rationCardMemberRepository.saveAll(rationCardMember);
		} else {
			throw new RationApplicationException("can't able to create");
		}

	}

}
