package com.mufg.ficoscorecheckservice.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;



@Component
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);
	boolean auditlog;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		LOGGER.debug("Request to: {}", request.getURI());
		//auditlog = AccountOpenServiceApplication.storeLog(Long.toString(System.currentTimeMillis()),
			//	request.getURI().toString(), request.getMethod().toString(), "", "", "", "", "Request");
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		LOGGER.debug("Response code: {}", response.getRawStatusCode());
		traceResponse(response);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		LOGGER.debug("=============================request begin================================================");
		LOGGER.debug("URI         : {}", request.getURI());
		LOGGER.debug("Method      : {}", request.getMethod());
		LOGGER.debug("Headers     : {}", request.getHeaders());
		LOGGER.debug("Request body: {}", new String(body, "UTF-8"));
		LOGGER.debug("=============================request end================================================");
	}

	private void traceResponse(ClientHttpResponse response) throws IOException {
		StringBuilder responseBodyBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
		String line = bufferedReader.readLine();
		while (line != null) {
			responseBodyBuilder.append(line).append(System.lineSeparator());
			line = bufferedReader.readLine();
		}

		/*auditlog = AccountOpenServiceApplication.storeLog(Long.toString(System.currentTimeMillis()), "", "", "",
				response.getStatusCode().toString(), response.getStatusText(), "", "Response");
*/
		LOGGER.debug("============================response begin==========================================");
		LOGGER.debug("Status code : {}", response.getStatusCode());
		LOGGER.debug("Status text : {}", response.getStatusText());
		LOGGER.debug("Headers     :");
		response.getHeaders().forEach((headerName, headerValue) -> LOGGER.trace("Header name : {}, header value: {}",
				headerName, headerValue));
		LOGGER.debug("Response body: {}", responseBodyBuilder.toString());
		LOGGER.debug("=======================response end=================================================");
	}

}
