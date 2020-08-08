package com.precise.model;

import org.springframework.web.multipart.MultipartFile;

public class OfferDream {
	private int differ;
	  MultipartFile file;
	public int getDiffer() {
		return differ;
	}
	public void setDiffer(int differ) {
		this.differ = differ;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
