package match;

import java.util.List;

public class Battle extends Event{
    List<Hero> groupBlue;
    List<Hero> groupRed;

    public Battle(List<Hero> heroBlue, List<Hero> heroRed){
        groupBlue = heroBlue;
        groupRed = heroRed;
        getHeroStat();
    }
    public void getHeroStat(){

    }
    public void fightOnLine(){
        for(Hero hero : groupBlue){
            hero.getPlayer().getRankGank();
        }
    }
}
