package com.iktpreobuka.elektronski_dnevnik.controllers;

public class RESTError {
	
		private Integer code;    
		private String message;
		
		public RESTError() {
			super();
		}

		public RESTError(Integer code, String message) {
			super();
			this.code = code;
			this.message = message;
		}

		public Integer getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
}
