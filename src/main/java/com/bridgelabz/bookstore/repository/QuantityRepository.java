package com.bridgelabz.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Quantity;
@Repository
public interface QuantityRepository  extends JpaRepository<Quantity, Long> 
{

	@Modifying
	@Query( value= "insert into Quantity_entity(quantity,book_id) values(?,?)",nativeQuery=true)
	void create(int quantity,long bookid);
	
	@Modifying
	@Query( value= "insert into cart_quantity(cart_id,quantity_id )values(?,?)",nativeQuery=true)
	void save(long cart_id, long quantity_id);
	
	@Modifying
	@Query( value = "select * from Quantity_entity  where quantity_id = :quantity_id", nativeQuery = true)
	Quantity  getquantId( long quantity_id);
	
	/*
	 * @Modifying
	 * 
	 * @Query( value=
	 * "insert into BookInfo (quantity) values(?) where book_id = :id ",nativeQuery=
	 * true) void adddquant( int quantity, long id);
	 */
	
//	@Modifying
//	@Query( value= "insert into BookInfo (quantity) values(?) where book_id = :id ",nativeQuery=true)
//	void savequant( int quantity, long id);
	
	
}
/*
 * @Override public void addtocartquantity(long cartId) { QuantityEntity en =
 * new QuantityEntity(); long id = 0; CartInformation cart=
 * cartrepo.fetchbyId(cartId); if(cart!=null) { id=en.getQuantity_id(); }
 * repo.add(cart.getCartId(),id);
 * 
 *  
 * }
 */