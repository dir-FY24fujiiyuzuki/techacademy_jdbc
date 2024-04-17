package dbSample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbSample.entity.Country;
import dbSample.util.DatabaseManager;

public class CountryDAO {
        //データベース接続と結果取得のための変数
        private PreparedStatement pstmt;
        private ResultSet rs;
        
        public List<Country> getCountryFromName(String name){
            //メソッドの結果として返すメソッド
            List<Country> results = new ArrayList<Country>();
            try {
                //１．２　ドライバを読み込みDBに接続
                Connection con = DatabaseManager.getConnection();
                
                //３．DBとやり取りする窓口（statementオブジェクト）の作成
                String sql  ="select * from country where Name = ?";
                pstmt = con.prepareStatement(sql);
                    
                //4.5 select分の実行と結果を格納・代入
                pstmt.setString(1,name);
                rs = pstmt.executeQuery();
                
                //6結果を表示
                while(rs.next()) {
                    //一行ずつcountryオブジェクトを生成して結果を詰める
                    Country country = new Country();
                    country.setName(rs.getString("Name"));
                    country.setPopulation(rs.getInt("Population"));
                    
                    //リストに格納
                    results.add(country);
                    
                    
                }
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch(SQLException e) {
                e.printStackTrace();
            }finally {
                if( pstmt!= null) {
                    try {
                        rs.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    
                }
            }
                if(pstmt!= null) {
                    try {
                        pstmt.close();
                    }catch(SQLException e) {
                        e.printStackTrace();
                        
                    }
                    }
                DatabaseManager.close();
                
                }
                
            
                
            }
            
        }
    


