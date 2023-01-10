package com.stg.service;

import java.util.List;

import com.stg.entity.Address;
import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.OrderStatus;
import com.stg.entity.Product;
import com.stg.entity.RationAdmin;
import com.stg.entity.RationCard;
import com.stg.entity.RationCardMember;
//import com.stg.entity.RationCardUser;
//import com.stg.entity.RationCard;
import com.stg.exception.RationApplicationException;

public interface RationAdminService {
	
	//=========================AdminLogin=========================
	public abstract RationAdmin rationAdminLogin(String rationAdminName);

	//==============================================================
	public abstract RationAdmin addRationAdmin(RationAdmin rationAdmin) throws RationApplicationException;

//	public abstract List<RationAdmin> readAllRationAdmin() throws RationApplicationException;

	public abstract RationAdmin readRationAdmin(int rationAdminId) throws RationApplicationException;

	// public abstract String updatePassword(int rationAdminId, String password)
	// throws RationApplicationException;

	public abstract String deleteRationAdmin(int rationAdminId) throws RationApplicationException;

	//================================Orders=============================================
	public abstract OrderDetails readOrders(int orderId) throws RationApplicationException;
	
	public abstract List<OrderDetails> readOrders() throws RationApplicationException;

	public abstract List<OrderDetails> readOrderBymonth(int month) throws RationApplicationException;
	public abstract String UpdateOrderStatus(int orderId,OrderStatus orderStatus)
			throws RationApplicationException;

	// PRODUCTS=======================================
	public abstract Product addProducts( Product products,int rationAdminId) throws RationApplicationException;
	
	
//	public abstract RationCard addRationCard(RationCard rationCard,int rationAdminId)
//			throws RationApplicationException;

	public abstract Product readByIdProducts(int productId ) throws RationApplicationException;
	public abstract List<Product> readProduct() throws RationApplicationException;
	public abstract Product updateProduct(int productId,Product product) throws RationApplicationException;

//	public abstract Product updateProduct(int rationAdminId, int productId, int quantity)
//			throws RationApplicationException;

	public abstract String deleteProduct(int productId) throws RationApplicationException;

	// Fixed product=======================================
	public abstract FixedProducts addFixedProducts( FixedProducts fixedProducts,int rationAdminId)
			throws RationApplicationException;
	
	public abstract FixedProducts readByIdFixedProducts( int productId ) throws RationApplicationException;
	public abstract List<FixedProducts> readFixedProduct() throws RationApplicationException;

//	public abstract FixedProducts updateFixedProduct(int productId,FixedProducts product)
//			throws RationApplicationException;
	public abstract FixedProducts updateFixedProduct(int productId,FixedProducts fixedProducts);

	public abstract String deleteFixedProduct( int productId) throws RationApplicationException;

//=======================================================================================================================
	public abstract String deleteRationCardMember(int rationAdminId, long rationCardNo, int memberId)
			throws RationApplicationException;

//	public abstract RationCardMember addRationCardMember(RationCardMember rationCardMember, long rationCardNo)
//			throws RationApplicationException;
	
	public abstract RationCardMember addRationCardMember(RationCardMember rationCardMember, int rationId)
			throws RationApplicationException;
	
	public abstract List<RationCardMember> addListRationCardMember(List<RationCardMember> rationCardMember, long rationCardNo)
			throws RationApplicationException;
	
	public abstract List<RationCardMember> addListRationCardMember(List<RationCardMember> rationCardMember,int rationId)
			throws RationApplicationException;
//=============================================================================================================
	public abstract List<RationCard> readUsers(int rationAdminId) throws RationApplicationException;

//	public abstract RationCardMember addRationCardMember(RationCardMember rationCardMember, int rationId)
//			throws RationApplicationException;
	
	public abstract RationCard addRationCard(RationCard rationCard,int rationAdminId)
			throws RationApplicationException;

	public abstract List<RationCard> readRationCard() throws RationApplicationException;
	public abstract RationCard readRationCard(long rationCardNo) throws RationApplicationException;
	public abstract String deleteRationCard( long rationCardNo) throws RationApplicationException;

//=========================================================================================Address
	public abstract Address addAddress(Address address,int rationId) throws RationApplicationException;

	public abstract List<Address> searchingArea(int rationAdminId, String area) throws RationApplicationException;

	
	public abstract String UpdateAdminIdToRationCard(int AdminId, int rationId) throws RationApplicationException;

	// Read RationCard
	
	// RationApplicationException;
//===========================================================Address================================
	//public Address addAddress(Address address, int rationId) throws RationApplicationException;
}
