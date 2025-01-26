package ru.kelcuprum.sailstatus.presence;

public abstract class AbstractPresence {
    public final TYPES type;
    public AbstractPresence(TYPES type){
        this.type = type;
    }
    abstract public void execute();
    abstract public boolean avaliable();

    enum TYPES{
        MENU(0),
        IN_GAME(1),
        MOD_SUPPORT(2);
        final int type;
        TYPES(int type){
            this.type = type;
        }
    }
}
