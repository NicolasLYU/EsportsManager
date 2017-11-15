package match;

import match.position.*;
import model.Team;

import java.util.*;


public class  Group {
    private String side;
    private Match match;
    private Team team;
    private Group enemy;
    private Carry carry;
    private Mid mid;
    private Offlane offlane;
    private Support support;
    private Hardsupport hardsupport;

    private List<Hero> heroTop;
    private List<Hero> heroMid;
    private List<Hero> heroBottom;
    private Map<String,List<Hero>> heroList;


    public Group(Team team, Match match, String side) {
        this.team = team;
        this.match = match;
        this.side = side;
        carry = new Carry(team.getCarry(), this);
        mid = new Mid(team.getMid(), this);
        offlane = new Offlane(team.getOfflane(), this);
        support = new Support(team.getSupport(), this);
        hardsupport = new Hardsupport(team.getHardsupport(), this);
        heroTop = new ArrayList<>();
        heroMid = new ArrayList<>();
        heroBottom = new ArrayList<>();
        heroList = new HashMap<String, List<Hero>>(){{
            put("top", heroTop);
            put("mid", heroMid);
            put("bottom", heroBottom);
        }};
    }

    public Match getMatch(){
        return match;
    }
    public Team getTeam() {
        return team;
    }
    public Group getEnemy(){
        return this.enemy;
    }
    public void setEnemy(Group enemy){
        this.enemy = enemy;
    }
    //英雄数据接口
    public Carry getCarry(){
        return carry;
    }
    public void setCarry(Carry carry){
        this.carry = carry;
    }
    public Mid getMid(){
        return mid;
    }
    public void setMid(Mid mid){
        this.mid = mid;
    }
    public Offlane getOfflane(){
        return offlane;
    }
    public void setOfflane(Offlane offlane){
        this.offlane = offlane;
    }
    public Support getSupport(){
        return support;
    }
    public void setSupport(Support support){
        this.support = support;
    }
    public Hardsupport getHardsupport(){
        return hardsupport;
    }
    public void setHardsupport(Hardsupport hardsupport){
        this.hardsupport = hardsupport;
    }

    public List<Hero> getHeroOnLane(String lane){
        return this.heroList.get(lane);
    }
    //分时段团队计划
    public void planOnLine(){
        double rand = Math.random();
        if(rand < 0.4){
            strategyGankSafelane();
        }else if(rand < 0.8){
            strategyGankMid();
        }else{
            strategyGankOfflane();
        }

    }

    //战术
    public void strategyGankSafelane(){
        System.out.println(this.getTeam().getName() + "decide to gank safelane...");
        if(this.side == "blue") {
            twoSupportGankLane("bottom");
        }else{
            twoSupportGankLane("top");
        }
    }
    public void strategyGankMid(){
        System.out.println(this.getTeam().getName() + "decide to gank mid lane...");
        twoSupportGankLane("mid");
    }
    public void strategyGankOfflane(){
        System.out.println(this.getTeam().getName() + "decide to gank offlane...");
        if(this.side == "blue") {
            twoSupportGankLane("top");
        }else{
            twoSupportGankLane("bottom");
        }
    }
    public void threeLineFarm(){
        heroAct(mid, "farm", "mid");
        if(this.side == "blue") {
            heroAct(carry, "farm", "bottom");
            heroAct(support, "farm", "top");
        }else{
            heroAct(carry, "farm", "top");
            heroAct(support, "farm", "bottom");
        }
    }
    public void twoSupportGankLane(String lane){
        heroAct(support, "gank", lane);
        heroAct(hardsupport, "gank", lane);
        this.getMatch().battle(lane, this.getHeroOnLane(lane), this.getEnemy().getHeroOnLane(lane));
    }
    public void heroAct(Hero hero, String action, String lane){
        hero.act(action,lane);
        List<Hero> list = heroList.get(lane);
        list.add(hero);
    }
}
