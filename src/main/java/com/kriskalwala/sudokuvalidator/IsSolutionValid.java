package com.kriskalwala.sudokuvalidator;

import com.kriskalwala.sudokuvalidator.IsSolutionValid;
import com.kriskalwala.sudokuvalidator.IsSolutionValid.isValid;

public class IsSolutionValid {
	
	enum isValid {
		VALID, INVALID;
	}

	isValid isValidfield = null;
	String Errorfield = null;

	public IsSolutionValid(isValid _isValid, String _vError) {
		this.isValidfield = _isValid;
		this.Errorfield = _vError;
	}

	public isValid getIsValidfield() {
		return isValidfield;
	}

	public void setIsValidfield(isValid isValidfield) {
		this.isValidfield = isValidfield;
	}

	public String getErrorfield() {
		return Errorfield;
	}

	public void setErrorfield(String errorfield) {
		Errorfield = errorfield;
	}

	@Override
	public String toString() {
		return "IsSolutionValid [isValid=" + isValidfield + ", error=" + Errorfield + "]";
	}
	
	
	public static IsSolutionValid valid() {
		System.out.println("print - IsSolutionValid [isValid=" + isValid.VALID + ", error=" + "" + "]");
		return new IsSolutionValid(isValid.VALID, "");
	}
	

	public static IsSolutionValid invalid(String error) {
		System.out.println("print - IsSolutionValid [isValid=" + isValid.INVALID + ", error=" + error + "]");
		return new IsSolutionValid(isValid.INVALID, error);
	}

}