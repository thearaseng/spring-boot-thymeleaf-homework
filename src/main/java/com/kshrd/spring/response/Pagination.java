package com.kshrd.spring.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kshrd.spring.exception.CustomGenericException;

import java.io.Serializable;


public class Pagination implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("page")
	private int page;
	
	@JsonProperty("limit")
	private int limit;
	
	@JsonProperty("total_count")
	private int totalCount;
	
	@JsonProperty("total_pages")
	private int totalPages;
	
	private int offset;
	
	public Pagination(){
		this(1, 15 , 0, 0);
	}	
	
	private Pagination(int page, int limit){
		this.page = page;
		this.limit = limit;
		this.totalCount = 0;
		this.totalPages = 0;
	}
	
	public Pagination(int page, int limit, int totalCount, int totalPages){
		this(page, limit);
		this.totalCount = totalCount;
		this.totalPages = totalPages;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int currentPage) {
		this.page = currentPage;
		this.offset();
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPages = (int) totalPages();
		if(this.totalPages() < this.page){
			throw new CustomGenericException("500", "The total pages has only " + this.totalPages() + " and your current page is " + this.page);
		}
	}

	public int getTotalPages() {
		return totalPages;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public boolean hasNextPage(){
		return this.nextPage() <= this.totalPages() ? true : false;
	}
	
	public boolean hasPreviousPage(){
		return this.previousPage() >= 1 ? true : false;
	}	

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private int totalPages(){
		return (int) Math.ceil((double)this.totalCount / limit);
	}
	
	public int offset(){
		return this.offset = (this.page - 1) * limit;
	}
	
	private int nextPage(){
		return this.page + 1;
	}
	
	private int previousPage(){
		return this.page - 1;
	}

}