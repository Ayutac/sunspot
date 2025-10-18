package studio.abos.mc.sunspot.common.blockentity;

public interface FlameBlockEntity {

    String CURRENT_FLAME_KEY = "current_flame";

    String MAX_FLAME_KEY = "max_flame";

    int getCurrentFlame();

    default boolean isPowered() {
        return getCurrentFlame() > 0;
    }

    void setCurrentFlame(int currentFlame);

    int getMaxFlame();

    void setMaxFlame(int maxFlame);
}
