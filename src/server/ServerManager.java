package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * サーバマネージャ(Singleton)
 *
 */
public class ServerManager {
	
	private static ServerManager serverManager;
	private static Connection sqlConnection;

	/**
	 * コンストラクタ(隠蔽)
	 */
	private ServerManager() {}
	
	/**
	 * インスタンス取得
	 * @return ServerManager
	 * @throws SQLException 
	 */
	public static ServerManager getIncetance() throws SQLException {
		if(ServerManager.serverManager == null) {
			ServerManager.serverManager = new ServerManager();
		}
		return ServerManager.serverManager;
	}
	
	/**
	 * SQLサーバとのコネクションを取得する
	 * @return
	 * @throws SQLException
	 */
	public Connection getSqlConnection() throws SQLException {
		if(ServerManager.sqlConnection == null) {
            sqlConnection = DriverManager.getConnection("jdbc:mysql://localhost/RECIPE", "root", "m00min");
		}
		return ServerManager.sqlConnection;
	}

}
