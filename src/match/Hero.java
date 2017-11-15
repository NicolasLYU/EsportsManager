package match;
import model.*;

import java.util.*;

public abstract class Hero{
    //游戏英雄信息
    private Player player;
    private int position;
    private Group group;
    //游戏内信息
    private int level = 1;
    private int numEquip = 0;
    private int exp = 0;
    private int gold = 0;
    private int health = 500;
    private int attack = 50;
    private int skillDamage = 100;
    private String action;
    private String lane;

    public Hero(Player player, Group group){
        this.player = player;
        this.position = player.getPosition();
        this.group = group;
    }
    public Hero(Player player, int position){
        this.player = player;
        this.position = position;
    }

    public Player getPlayer(){
        return player;
    }
    public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }
    public Group getGroup(){
        return group;
    }

    public int getExp(){
        return exp;
    }
    public void setExp(int exp){
        this.exp = exp;
        if(exp > (level * 200 + level * level * 10) && level <= 25){
            levelUp();
        }
    }
    public int getGold(){
        return gold;
    }
    public void setGold(int gold){
        this.gold = gold;
        purchaseEquip();
    }
    public String getAction(){
        return action;
    }
    public void setAction(String action){
        this.action = action;
    }
    public String getLane(){
        return lane;
    }
    public void setLane(String lane){
        this.lane = lane;
    }
    public void setLaneAction(String lane, String action){
        this.lane = lane;
        this.action = action;
    }

    public void farm(){
        int goldGrow = this.player.getRankFarm() * 5;
        this.gold += goldGrow;


    }
    public void gank(){
        if(this.getGroup().getEnemy().getHeroOnLane(this.lane).isEmpty() ){
            int goldGrow = this.player.getRankFarm() * 3;
            this.gold += goldGrow;
        }else{
            this.getGroup().getMatch().battle(this.lane, this.getGroup().getHeroOnLane(this.lane), this.getGroup().getEnemy().getHeroOnLane(this.lane));
            //Battle battle = new Battle(this.getGroup().getHeroOnLane(this.lane), this.getGroup().getEnemy().getHeroOnLane(this.lane));

        }

    }
    public void act(String action, String lane){
        setLaneAction(lane, action);
        if(action == "farm"){
            this.farm();
        }else if(action == "gank"){
            this.gank();
        }
    }
    public synchronized void levelUp(){
        this.attack += 5;
        this.health += 50;
        if(this.level <= 10)
            this.skillDamage += 50;
        this.level += 1;
    }
    public synchronized void purchaseEquip(){
        if(this.numEquip < 6 && this.gold >= 2000){
            this.health += 200;
            this.attack += 20;
            this.numEquip++;
        }else if(this.numEquip == 6 && this.gold >= 5000){
            this.health += 500;
            this.attack += 50;
        }
    }
    public void useSkill(Hero enemy){

    }
}
