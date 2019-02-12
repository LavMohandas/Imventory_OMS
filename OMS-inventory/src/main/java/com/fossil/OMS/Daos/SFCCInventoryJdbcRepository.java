package com.fossil.OMS.Daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fossil.OMS.Model.SFCCInventory;

@Repository
public class SFCCInventoryJdbcRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class InventoryRowMapper implements RowMapper<SFCCInventory> {
		@Override
		public SFCCInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
			SFCCInventory inv = new SFCCInventory();
			inv.setCatentryId(rs.getLong("sfcc_catentryId"));
			inv.setQuantity(rs.getInt("sfcc_quantity"));
			inv.setStoreId(rs.getInt("sfcc_storeid"));
			return inv;
		}

	}

	public List<SFCCInventory> findAll() {
		return jdbcTemplate.query("select * from SFCCInventory", new InventoryRowMapper());
	}

	public SFCCInventory findByCatentryId(long id) {
		return jdbcTemplate.queryForObject("select * from SFCCInventory where sfcc_catentryId=?", new Object[] { id },
				new BeanPropertyRowMapper<SFCCInventory>(SFCCInventory.class));
	}

	public int deleteByCatentryId(long id) {
		return jdbcTemplate.update("delete from SFCCInventory where sfcc_catentryId=?", new Object[] { id });
	}

	public int insert(SFCCInventory inv) {
		return jdbcTemplate.update("insert into SFCCInventory (sfcc_catentryId, sfcc_quantity, sfcc_storeId) " + "values(?,  ?, ?)",
				new Object[] { inv.getCatentryId(), inv.getQuantity(), inv.getStoreId() });
	}

	public int update(SFCCInventory inv) {
		return jdbcTemplate.update("update SFCCInventory " + " set sfcc_quantity = ? " + " where sfcc_catentryId = ?",
				new Object[] {inv.getQuantity(),inv.getCatentryId() });
	}

}
