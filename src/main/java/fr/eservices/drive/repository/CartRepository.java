package fr.eservices.drive.repository;

import org.springframework.data.repository.CrudRepository;

import fr.eservices.drive.model.Cart;

public interface CartRepository 
extends CrudRepository<Cart,Long>
{

}
