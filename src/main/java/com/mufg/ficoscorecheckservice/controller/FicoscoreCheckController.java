package com.mufg.ficoscorecheckservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mufg.ficoscorecheckservice.dao.FicoscoreCheckDAO;
import com.mufg.ficoscorecheckservice.model.FicoscoreCheckModel;


@RestController
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableEurekaClient
@CrossOrigin
@RequestMapping("/mufg/api")
@CrossOrigin
public class FicoscoreCheckController {

	private static final Logger logger = LogManager.getLogger(FicoscoreCheckController.class);

	@Autowired
	private FicoscoreCheckDAO ficoscoreCheckDAO;

	@RequestMapping(value = "/ficoscorecheck/ssn/{ssnId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<FicoscoreCheckModel> ficoscoreCheck(@PathVariable String ssnId) {

		logger.debug("Inside FicoScore Check Controller");

		FicoscoreCheckModel ficoscoreCheckModel = ficoscoreCheckDAO.fraudCheck(ssnId);
		logger.debug("Fico Score Check Status from DAO " + ficoscoreCheckModel.isStatus());
		if (!(ficoscoreCheckModel.isStatus())) {
			logger.debug("Fico Score Check Status " + ficoscoreCheckModel.isStatus());
			return ResponseEntity.ok(ficoscoreCheckModel);
		}
		return ResponseEntity.ok(ficoscoreCheckModel);
	}
}
