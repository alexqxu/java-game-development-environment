package api;

/**
 * Provides functionality to support VFX and SFX by checking in the game for when it is appropriate to incite the
 * effect and inciting the effect it is appropriate
 */
public interface EffectsAPI {

    /**
     * Returns whether the corresponding in-game event occurred that would incite the effect
     * @return true if the corresponding in-game event occurred; false otherwise
     */
    public boolean isEffectConditionMet();

    /**
     * Execute the effect in the gameplay
     */
    public void triggerEffect();

}
