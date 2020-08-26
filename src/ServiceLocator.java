
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ServiceLocator {
	public static ServiceLocator serviceLocator;

	public final static int APP_DB = 1;
	public final static int A2M_DB = 2;
	public final static int LW_DB = 3;
	public final static int MASTER_DB = 4;
	public final static int SURVEY_DB = 5;
	public final static int WORKFORCE_DB = 6;
	
	// private final static String JAVA_ENV = "";
	private final static String JAVA_ENV = "java:/";
	public final static String APP_DATA_SOURCE = "jdbc/application";
	public final static String A2M_DATA_SOURCE = "jdbc/A2M";
	public final static String LW_DATA_SOURCE = "jdbc/A2M";
	public final static String MASTER_DATA_SOURCE = "jdbc/master";
	public final static String SURVEY_DATA_SOURCE = "jdbc/sv";
	public final static String WORKFORCE_DATA_SOURCE = "jdbc/workforce";
	public final static String A2M_SCHEMA = "A2M_PS.dbo";
	public final static String APP_SCHEMA = "HCM.dbo";
	public final static String MASTER_SCHEMA = "EAF_MASTER.EAF_MASTER";
	public final static String SURVEY_SCHEMA = "EAF_MASTER.dbo";

	public static ServiceLocator getInstance() {
		if (serviceLocator == null) {
			serviceLocator = new ServiceLocator();
		}
		return serviceLocator;
	}

	public Connection getConnection(int dbType) throws Exception {
		try {
			InitialContext ctx = new InitialContext();
			Object obj = null;
			switch (dbType) {
			case ServiceLocator.APP_DB:
				obj = ctx.lookup(JAVA_ENV + APP_DATA_SOURCE);
				break;
			case ServiceLocator.A2M_DB:
				obj = ctx.lookup(JAVA_ENV + A2M_DATA_SOURCE);
				break;
			case ServiceLocator.LW_DB:
				obj = ctx.lookup(JAVA_ENV + LW_DATA_SOURCE);
				break;
			case ServiceLocator.MASTER_DB:
				obj = ctx.lookup(JAVA_ENV + MASTER_DATA_SOURCE);
				break;
			case ServiceLocator.SURVEY_DB:
				obj = ctx.lookup(JAVA_ENV + SURVEY_DATA_SOURCE);
				break;
			case ServiceLocator.WORKFORCE_DB:
				obj = ctx.lookup(JAVA_ENV + WORKFORCE_DATA_SOURCE);
				break;
			default:
				obj = ctx.lookup(JAVA_ENV + APP_DATA_SOURCE);
				break;
			// return
			// ((DataSource)ctx.lookup(JAVA_ENV+XENOZ_DATA_SOURCE)).getConnection();
			}
			DataSource dataSrc = (DataSource) javax.rmi.PortableRemoteObject
					.narrow(obj, DataSource.class);
			return dataSrc.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.toString());
		}
	}
	
	public Connection getConnectionLocal() throws Exception {
		try {
			InitialContext ctx = new InitialContext();
			Object obj = null;
			//DriverManager.registerDriver(new org.postgresql.Driver());
			//obj = DriverManager.getConnection("jdbc:postgresql://192.168.0.221:5432/hcmoic", "hcm", "avalant@dmin0hcm");
			//obj = DriverManager.getConnection("jdbc:postgresql://61.90.160.212:5432/minervadb", "minerva", "Mu#@98731!");
			return (Connection) obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.toString());
		}
	}
	

	public final void closeConnection(Connection con) throws Exception {

		try {

			try {
				if (con != null) {
					con.commit();
				}
			} catch (Exception e) {
			}
			
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
			}
			
			con = null;

		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	/**
	 * close database connection
	 * 
	 * @param con
	 * @param stm
	 * 
	 * @throws Exception
	 */
	public final void closeConnection(Connection con, Statement stm)
			throws Exception {

		try {

			try {

				if (con != null) {
					con.commit();
				}
			} catch (Exception e) {
			}

			if (stm != null) {

				try {
					stm.close();
				} catch (Exception e) {
				}
			}

			if (con != null) {

				try {
					con.close();
				} catch (Exception e) {
				}
			}

			stm = null;
			con = null;
		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	/**
	 * close database connection
	 * 
	 * @param con
	 * @param ps
	 * 
	 * @throws Exception
	 */
	public final void closeConnection(Connection con, PreparedStatement ps)
			throws Exception {

		try {

			try {
				if (con != null) {
					con.commit();
				}
			} catch (Exception e) {
			}

			if (ps != null) {

				try {
					ps.close();
				} catch (Exception e) {
				}
			}

			if (con != null) {

				try {
					con.close();
				} catch (Exception e) {
				}
			}

			ps = null;
			con = null;
		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	/**
	 * close database connection
	 * 
	 * @param con
	 * @param rs
	 * @param ps
	 * 
	 * @throws Exception
	 */
	public final void closeConnection(Connection con, ResultSet rs,
			PreparedStatement ps) throws Exception {

		try {

			try {

				if (con != null) {
					con.commit();
				}
			} catch (Exception e) {
			}

			if (rs != null) {

				try {
					rs.close();
				} catch (Exception e) {
				}
			}

			if (ps != null) {

				try {
					ps.close();
				} catch (Exception e) {
				}
			}

			if (con != null) {

				try {
					con.close();
				} catch (Exception e) {
				}
			}

			rs = null;
			ps = null;
			con = null;
		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public final void closeConnection(Connection con, CallableStatement cs)
			throws Exception {

		try {

			try {

				if (con != null) {
					con.commit();
				}
			} catch (Exception e) {
			}

			if (cs != null) {

				try {
					cs.close();
				} catch (Exception e) {
				}
			}
			if (con != null) {

				try {
					con.close();
				} catch (Exception e) {
				}
			}

			cs = null;
			con = null;
		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public final void closeConnection(CallableStatement cs) throws Exception {

		try {
			if (cs != null) {

				try {
					cs.close();
				} catch (Exception e) {
				}
			}

			cs = null;
		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public final void closeConnection(ResultSet rs, PreparedStatement ps)
			throws Exception {

		try {

			if (rs != null) {

				try {
					rs.close();
				} catch (Exception e) {
				}
			}

			if (ps != null) {

				try {
					ps.close();
				} catch (Exception e) {
				}
			}

			rs = null;
			ps = null;
		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
	}

	public final void closeConnection(PreparedStatement ps) throws Exception {

		try {
			if (ps != null) {

				try {
					ps.close();
				} catch (Exception e) {
				}
			}

			ps = null;
		} catch (Exception ex) {
			throw new SQLException(ex.getMessage());
		}
	}
}
