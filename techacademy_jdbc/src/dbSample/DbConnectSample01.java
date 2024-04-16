package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectSample01 {
    
    public static void main(String[] args) {
   //データベース接続と結果取得のための変数
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    

    try {
     //1.ドライバーのクラスをJava上で読み込む
         Class.forName("com.mysql.cj.jdbc.Driver");
   
     //2.DBと接続する
     con = DriverManager.getConnection(
              "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
              "root",
              "yuGzuD0819@"
      );
                
                   
     //3.DBとやり取りする窓口（オブジェクトの作成
      stmt = con.createStatement();
                    
         
     //4,5.select分の実行と結果を格納/代入
         String sql = "SELECT* FROM country LIMIT 50";
         rs = stmt.executeQuery(sql);
     //6.結果を表示する
         while( rs.next()) {
             //Name列の値を取得
             String name = rs.getString("Name");
             //population列の値取得←追記
             int population = rs.getInt("Population"); //追記
             
            //取得した値を表示
             System.out.println(name);
             System.out.println(population);//追記
         }
          } catch (ClassNotFoundException e) {
               System.err.println("JDBCドライバーのロードに失敗しました。");
                e.printStackTrace();
               
            }catch(SQLException e) {
               System.err.println("データベースに異常が発生しました。");
               e.printStackTrace();
            
           }finally {
          //７．接続を閉じる
               if(rs != null) {
                   try {  
                       rs.close();
                   }catch(SQLException e) {
                       System.err.println("Resultをと実時のエラーが発生しました。");
                       e.printStackTrace();
                   }
               }
              
               if( stmt != null ){
                   try {
                       stmt.close();
                   } catch (SQLException e) {
                       System.err.println("Statementを閉じるときにエラーが発生しました。");
                       e.printStackTrace();
                   }
               }
               if(con!= null) {
                   try {
                       con.close();
                   }catch(SQLException e) {
                       System.err.println("でーたべーすが接続時にエラーが発生しました。");
                       e.printStackTrace();
                     
                  
                           
                   
               }
           }
    }
    
              
           
       
    
            
    }   
}

