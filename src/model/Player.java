package model;

public class Player {
    //基本信息
    private String name;
    private int position;
    //能力属性
    private int rankLine;
    private int rankFarm;
    private int rankGank;
    private int rankDamage;
    private int rankTank;
    private int rankAntiGank;
    private int rankReaction;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }
    public int getRankLine(){
        return rankLine;
    }
    public void setRankLine(int rankLine){
        this.rankLine = rankLine;
    }
    public int getRankFarm(){
        return rankFarm;
    }
    public void setRankFarm(int rankFarm){
        this.rankFarm = rankFarm;
    }
    public int getRankGank(){
        return rankGank;
    }
    public void setRankGank(int rankGank){
        this.rankGank = rankGank;
    }
    public int getRankDamage(){
        return rankDamage;
    }
    public void setRankDamage(int rankDamage){
        this.rankDamage = rankDamage;
    }
    public int getRankTank(){
        return rankTank;
    }
    public void setRankTank(int rankTank){
        this.rankTank = rankTank;
    }
    public int getRankAntiGank(){
        return rankAntiGank;
    }
    public void setRankAntiGank(int rankAntiGank){
        this.rankAntiGank = rankAntiGank;
    }
    public int getRankReaction(){
        return rankReaction;
    }
    public void setRankReaction(int rankReaction){
        this.rankReaction = rankReaction;
    }
}
