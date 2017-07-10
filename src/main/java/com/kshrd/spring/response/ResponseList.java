package com.kshrd.spring.response;

import java.util.List;

/**
 * 
 * @author Tola
 *	Created Date: 2017/07/03
 *
 */
public class ResponseList<T> extends Response {
	/*
	 * Response list class using with multiple object
	 */

	private List<T> data;
	
	private Pagination1 pagination;
	
	public ResponseList() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ResponseList(String message, boolean status, List<T> data,Pagination1 pagination) {
		super(message, status);
		this.data = data;
		this.pagination = pagination;
	}

	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * @return the pagination
	 */
	public Pagination1 getPagination() {
		return pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(Pagination1 pagination) {
		this.pagination = pagination;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseList [data=" + data + ", pagination=" + pagination + "]";
	}

}