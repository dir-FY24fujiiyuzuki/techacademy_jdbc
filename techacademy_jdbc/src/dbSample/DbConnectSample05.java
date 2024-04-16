package dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





public class DbConnectSample05 {
    
    public static void main(String[] args) {
   //データベース接続と結果取得のための変数
    Connection con = null;
    PreparedStatement pstmt = null;
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
     
     String sql = "SELECT * FROM country WHERE Name = ?";
  //3.DBとやり取りする窓口（オブジェクトの作成
      pstmt = con.prepareStatement(sql);
                    
         
     //4,5.6 UPDATEの実行と結果を格納・代入
      System.out.print("CountryCodeを入力してください >"); 
      String str1 = keyIn();
      
      System.out.println("Populationを数字で入力してください >");
      int num1 = Integer.parseInt(keyIn());
      
      pstmt.setString(1,  str1);
      pstmt.setInt(2, num1);
      
      int count = pstmt.executeUpdate();
      System.out.println(count);
         
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
                       System.err.println("resultSetを閉じるときにエラーが発生しました");
                       e.printStackTrace();
                   }
               }
              
               if( pstmt != null){
                   try {
                       pstmt.close();
                   } catch (SQLException e) {
                       System.err.println("Statementを閉じるときにエラーを発生しました。");
                       e.printStackTrace();
                   }
               }
              if(con!= null) {
                   try {
                       con.close();
                   }catch(SQLException e) {
                       System.out.println("データベース切断時にエラーが発生しました。");
                       e.printStackTrace();
                   } 
                 
                   
              }
       }
    }
    
              
     /*
      * キーボードから入力された値をStringで返す
      * 　引数：なし　
      * 戻り値：入力された文字列  
      */
    private static String keyIn() {
        String line = null;
        try {
                BufferedReader key = new BufferedReader(new InputStreamReader
                        (System.in));
                line = key.readLine();
        } catch (IOException e) {

        
    }
       
        return line;
            
    }   
}

