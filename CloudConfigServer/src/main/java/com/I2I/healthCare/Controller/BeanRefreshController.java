package com.I2I.healthCare.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BeanRefreshController works as End point for Refreshing the Beans when
 * changes made in GIT.
 * 
 * @author Subash_Sakthivel
 * @since 21.02
 *
 */
@RestController
@RefreshScope
@RequestMapping("/beans")
public class BeanRefreshController {

	Logger logger = LoggerFactory.getLogger(BeanRefreshController.class);

	@PostMapping(path = "/reset")
	public ResponseEntity<String> doRefresh(@RequestBody String body) {

		int responseCode = 0;
		try {

			final String POST_PARAMS = "";
			URL obj = new URL("http://localhost:8888/actuator/bus-refresh");
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod("POST");
			postConnection.setRequestProperty("Content-Type", "application/json");
			postConnection.setDoOutput(true);
			OutputStream os = postConnection.getOutputStream();
			os.write(POST_PARAMS.getBytes());
			os.flush();
			os.close();
			responseCode = postConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_CREATED) {
				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				logger.info(response.toString());
				logger.info(" — — — -Configuration Refreshed Successfully — — — -");
			} else {
				logger.error("POST NOT WORKED");
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}

		return new ResponseEntity<>("Configuration Refreshed Sucessfully", HttpStatus.OK);
	}
}
