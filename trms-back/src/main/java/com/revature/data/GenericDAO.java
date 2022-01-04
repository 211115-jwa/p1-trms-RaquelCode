package com.revature.data;

import java.util.Set;



// DAO: data access object
// an object that is designed for exclusively accessing data (e.g. in a database)
// the <D> generic type represents the type of Data you are working with (person, pet, etc)

/*
 * why use generics rather than just "Object"?
 * generics give you compile time type safety -
 * you set the type in your code so that when
 * the code is compiled, that type is set in
 * stone and no other type of object can get in.
 * just using the "Object" type would allow
 * any object type with no restrictions at
 * compile time.
 */
public interface GenericDAO<D> {
	// accessing the database should use CRUD operations:
	// create, read, update, delete
	public int create(D dataToAdd);
	public D getById(int id);
	public Set<D> getAll();
	public void update(D dataToUpdate);
	public void delete(D dataToDelete);
}
