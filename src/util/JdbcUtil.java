package util;

import model.Team;
import model.Player;

import java.sql.*;

public class JdbcUtil {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/esportsmanager";

    static final String USER = "root";
    static final String PASS = "";

    Connection conn = null;
    Statement stat = null;


    public Team getTeamByName(String name){
        Team team = new Team();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stat = conn.createStatement();
            String sql = "SELECT * FROM team WHERE name = ? ";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,name);
            ResultSet rs =ptmt.executeQuery();
            while(rs.next()){
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                team.setCarry(getPlayerByName(rs.getString("carry")));
                team.setMid(getPlayerByName(rs.getString("mid")));
                team.setOfflane(getPlayerByName(rs.getString("offlane")));
                team.setSupport(getPlayerByName(rs.getString("support")));
                team.setHardsupport(getPlayerByName(rs.getString("hardsupport")));
            }
            stat.close();
            conn.close();
            ptmt.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } finally{
            // 关闭资源
            try{
                if(stat!=null) stat.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return team;
    }
    public Player getPlayerByName(String name){
        Player player = new Player();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stat = conn.createStatement();
            String sql = "SELECT * FROM player WHERE name =?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,name);
            ResultSet rs =ptmt.executeQuery();
            while(rs.next()){
                player.setName(rs.getString("name"));
                player.setPosition(rs.getInt("position"));
                player.setRankLine(rs.getInt("rankLine"));
                player.setRankFarm(rs.getInt("rankFarm"));
                player.setRankGank(rs.getInt("rankGank"));
                player.setRankDamage(rs.getInt("rankDamage"));
                player.setRankTank(rs.getInt("rankTank"));
                player.setRankAntiGank(rs.getInt("rankAntiGank"));
                player.setRankReaction(rs.getInt("rankReaction"));
            }
            stat.close();
            conn.close();
            ptmt.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }  finally{
            // 关闭资源
            try{
                if(stat!=null) stat.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return player;
    }

}
