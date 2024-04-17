package dbSample.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    //データベース接続と結果取得のための変数
    public static Connection con;
    
    public static Connection getConnection()throws SQLException,ClassNotFoundException{
        //1ドライバのクラスをjava上で読み込む
        Class.forName("com.mysql.cj.jdbc.Driver\"");
        //2DBを接続
        con = DriverManager.getConnection(
                "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "yuGzuD0819@"
                );
        return con;
    }
    public static void close() {
        if(con != null)
            try {
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
           
            }
   
            
                
    }

}
