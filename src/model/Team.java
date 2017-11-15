package model;
import util.JdbcUtil;

import java.sql.*;
public class Team {

    private static Team[] teamlist;
    private static int numTeam;

    private int id;
    private String name;

    private Player carry;
    private Player mid;
    private Player offlane;
    private Player support;
    private Player hardsupport;

    public Team(){}
    public Team(String name){
        JdbcUtil db = new JdbcUtil();
        Team team = db.getTeamByName(name);
        this.id = team.getId();
        this.name = team.getName();
        this.carry = team.getCarry();
        this.mid = team.getMid();
        this.offlane = team.getOfflane();
        this.support = team.getSupport();
        this.hardsupport = team.getHardsupport();

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Player getCarry(){
        return carry;
    }
    public void setCarry(Player carry){
        this.carry = carry;
    }
    public Player getMid(){
        return mid;
    }
    public void setMid(Player mid){
        this.mid = mid;
    }
    public Player getOfflane(){
        return offlane;
    }
    public void setOfflane(Player offlane){
        this.offlane = offlane;
    }
    public Player getSupport(){
        return support;
    }
    public void setSupport(Player support){
        this.support = support;
    }
    public Player getHardsupport(){
        return hardsupport;
    }
    public void setHardsupport(Player hardsupport){
        this.hardsupport = hardsupport;
    }

    public void init(){

    }

}
