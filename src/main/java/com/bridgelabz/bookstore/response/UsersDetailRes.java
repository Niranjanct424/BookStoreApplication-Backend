/**
 * 
 */
package com.bridgelabz.bookstore.response;

/**
 * @author HP
 *
 */
public class UsersDetailRes {
	private String token;

	private int statuscode;

	private Object obj;

	public UsersDetailRes(String token,int statuscode,Object obj)
	{
		this.token=token;

		this.statuscode=statuscode;

		this.obj=obj;
	}

}
