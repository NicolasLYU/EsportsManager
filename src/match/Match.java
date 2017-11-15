package match;

import model.*;

import java.util.ArrayList;
import java.util.List;


public class Match {
    private Team teamBlue;
    private Team teamRed;
    private Group groupBlue;
    private Group groupRed;
    private BattleField battleField;
    private int time = 0;
    private boolean end = false;



    public Match(Team team1, Team team2){
        teamBlue = team1;
        teamRed = team2;

    }

    public void start(){
        System.out.println("Match start! " + teamBlue.getName() + " vs " + teamRed.getName());
        groupBlue = new Group(teamBlue,this, "blue");
        groupRed = new Group(teamRed,this, "red");
        groupBlue.setEnemy(groupRed);
        groupRed.setEnemy(groupBlue);
        battleField = new BattleField(groupBlue,groupRed);
        System.out.println("The team have chosen the side and pick the hero ...");
        while(end == false){
            if(time <= 12){
                periodOnLine();
            }
            else if(time > 12 && time<= 30){
                periodGank();
            }
            else if(time>30){
                periodFight();
            }
            time += 2;
        }
    }
    public void periodOnLine(){
        groupBlue.threeLineFarm();
        groupRed.threeLineFarm();
        groupBlue.planOnLine();
        groupRed.planOnLine();
        System.out.println("time is " + time);
        //GroupTaskThread groupBlueTask = new GroupTaskThread(groupBlue);
        //GroupTaskThread groupRedTask = new GroupTaskThread(groupRed);

        /*
        farm(groupBlue.getOfflane(), groupRed.getCarry());
        farm(groupBlue.getMid(), groupRed.getMid());
        farm(groupBlue.getCarry(), groupRed.getOfflane());

        gank(groupBlue,groupRed);
        gank(groupRed,groupBlue);*/
    }
    public void periodGank(){}
    public void periodFight(){
        this.end = true;
    }

    public void farm(Hero blue, Hero red){
        int blueRankLine = blue.getPlayer().getRankLine();
        int redRankLine = red.getPlayer().getRankLine();
        int blueRankFarm = blue.getPlayer().getRankFarm();
        int redRankFarm = red.getPlayer().getRankFarm();
        int blueGold = blue.getGold();
        int redGold = red.getGold();
        float redRatio = (float)redRankLine/(float)blueRankLine;
        float blueRatio = (float)blueRankLine/(float)redRankLine;
        blue.setGold((int)(blueGold + blueRatio * blueRankFarm));
        red.setGold((int)(redGold + redRatio * redRankFarm));
    }
    public void gank(Group gankGroup, Group gankedGroup){
        double rand = Math.random();
        if(rand < 0.2){
            gankOfflane(gankGroup, gankedGroup);
        }else if(rand >= 0.5){

        }else{

        }
    }
    public void gankOfflane(Group gankGroup, Group gankedGroup){
        Hero support = gankGroup.getSupport();
        Hero hardsupport = gankGroup.getHardsupport();
        Hero carry = gankGroup.getCarry();
        Hero offlane = gankedGroup.getOfflane();
        double pos = (support.getPlayer().getRankGank() + hardsupport.getPlayer().getRankGank() + carry.getPlayer().getRankLine())/offlane.getPlayer().getRankLine()*4;
        if(pos >= Math.random()){
            System.out.println("Gank success...");

        }else{
            System.out.println("Gank failed...");
        }

    }
    //同一条线上 两边的战斗
    public void battle(String lane, List<Hero> groupGank, List<Hero> groupGanked){
        int rankGank = 0;
        int rankAntiGank = 0;
        int maxReaction = 0;
        ArrayList<Hero> sequenceAction = new ArrayList<>(groupGank);
        sequenceAction.addAll(groupGanked);
        for(int i=0;i<sequenceAction.size();i++) {
            Hero key = sequenceAction.get(i);
            int position = i;
            for (int j = i + 1; j < sequenceAction.size(); j++) {
                if (sequenceAction.get(j).getPlayer().getRankReaction() > key.getPlayer().getRankReaction()){
                    key = sequenceAction.get(j);
                    position = j;
                }
            }
            sequenceAction.set(position, sequenceAction.get(i));
            sequenceAction.set(i, key);

        }
        for(int i =0;i<sequenceAction.size();i++){
            if(groupGank.contains(sequenceAction.get(i))) {
                actionHero(sequenceAction.get(i), groupGanked);
            }else if(groupGanked.contains(sequenceAction.get(i))){
                actionHero(sequenceAction.get(i),groupGank);
            }
        }
    }
    public void actionHero(Hero hero, List<Hero> enemies){
        int maxAntiGank=0;
        Hero heroAttacked = enemies.get(0);
        //得到攻击目标heroAttacked
        for(Hero enemy : enemies){
            if(enemy.getPlayer().getRankAntiGank() > maxAntiGank){
                maxAntiGank = enemy.getPlayer().getRankAntiGank();
                heroAttacked = enemy;
            }
        }
        hero.useSkill(heroAttacked);
    }

}
