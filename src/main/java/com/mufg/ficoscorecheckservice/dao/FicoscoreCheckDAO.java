package com.mufg.ficoscorecheckservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mufg.ficoscorecheckservice.model.FicoscoreCheckModel;

@Configuration
@Repository
public class FicoscoreCheckDAO {

	private static final Logger logger = LogManager.getLogger(FicoscoreCheckDAO.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	FicoscoreCheckModel ficoscoreCheckModel;

	/**
	 * @return
	 */
	public java.sql.Date getCurrentDatetime() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	/**
	 * @param ssnId
	 * @return
	 * @throws ParseException
	 */
	public FicoscoreCheckModel fraudCheck(String ssnId) {

		logger.debug("SSNID : " + ssnId);

		String query = "select ssn,score,validfrom,validto from SmartBankOBInnovationDB_1_1.FicoScore"
				+ " where ssn = ?  and validfrom < ? and validto > ? ";

		Connection con = null;
		PreparedStatement ps = null;
		ficoscoreCheckModel = new FicoscoreCheckModel();
		ficoscoreCheckModel.setStatus(true);
		try {

			logger.debug("Inide Fico Score Check DAO Service" + getCurrentDatetime());
			con = jdbcTemplate.getDataSource().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, ssnId);
			ps.setDate(2, getCurrentDatetime());
			ps.setDate(3, getCurrentDatetime());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				ficoscoreCheckModel.setSsn(rs.getString("ssn"));
				ficoscoreCheckModel.setFscore(rs.getInt("score"));
				ficoscoreCheckModel.setVfdate(rs.getDate("validfrom"));
				ficoscoreCheckModel.setVtdate(rs.getDate("validto"));
				logger.debug("ficoscoreCheckModel.getFscore : " + ficoscoreCheckModel.getFscore());
				if (ficoscoreCheckModel.getFscore() >= 700) {
					ficoscoreCheckModel.setStatus(false);
				} else {
					ficoscoreCheckModel.setStatus(true);
				}

			}
			ps.close();
			con.close();
			rs.close();

		} catch (SQLException e) {
			logger.debug("SQL exception : Catch block");
		} finally {
			try {
				if (null != ps) {
					ps.close();
				}
				if (null != con) {
					con.close();
				}
			} catch (SQLException e) {
				logger.debug("SQL exception : Finaly Block ");
			}
		}
		return ficoscoreCheckModel;
	}

}
