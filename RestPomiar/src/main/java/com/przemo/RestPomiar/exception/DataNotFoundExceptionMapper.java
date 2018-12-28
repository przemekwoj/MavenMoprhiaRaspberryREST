package com.przemo.RestPomiar.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.przemo.RestPomiar.error.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), Status.BAD_REQUEST, "wrong data");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
