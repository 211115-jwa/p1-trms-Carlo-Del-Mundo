package com.revature.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Role;
import com.revature.data.RoleDAO;
import com.revature.utils.ConnectionUtil;

public class RolePostgres implements RoleDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Role getByID(int id) {
		// TODO Auto-generated method stub
		Role role = null;
		try (Connection conn = connUtil.getConnection()) {
			String sql = "SELECT * FROM user_role WHERE role_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if(resultSet.next()) {
				role = new Role();
				role.setRoleId(id);
				role.setName(resultSet.getString("role_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

}
