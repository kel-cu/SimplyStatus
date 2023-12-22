package ru.kelcuprum.simplystatus.info;

public class DiscordRichPresence {
    public DiscordRichPresence(){}

    public String state;

    /**
     * What the player is currently doing.
     * <br>Example: "Competitive - Captain's Mode", "In Queue", "Unranked PvP"
     *
     * <p><b>Maximum: 128 characters</b>
     */
    public String details;

    /**
     * Unix timestamp (seconds) for the start of the game.
     * <br>Example: 1507665886
     */
    public long startTimestamp;

    /**
     * Unix timestamp (seconds) for the start of the game.
     * <br>Example: 1507665886
     */
    public long endTimestamp;

    /**
     * Name of the uploaded image for the large profile artwork.
     * <br>Example: "default"
     *
     * <p><b>Maximum: 32 characters</b>
     */
    public String largeImageKey;

    /**
     * Tooltip for the largeImageKey.
     * <br>Example: "Blade's Edge Arena", "Numbani", "Danger Zone"
     *
     * <p><b>Maximum: 128 characters</b>
     */
    public String largeImageText;

    /**
     * Name of the uploaded image for the small profile artwork.
     * <br>Example: "rogue"
     *
     * <p><b>Maximum: 32 characters</b>
     */
    public String smallImageKey;

    /**
     * Tooltip for the smallImageKey.
     * <br>Example: "Rogue - Level 100"
     *
     * <p><b>Maximum: 128 characters</b>
     */
    public String smallImageText;

    /**
     * ID of the player's party, lobby, or group.
     * <br>Example: "ae488379-351d-4a4f-ad32-2b9b01c91657"
     *
     * <p><b>Maximum: 128 characters</b>
     */
    public String partyId;

    /**
     * Current size of the player's party, lobby, or group.
     * <br>Example: 1
     */
    public int partySize;

    /**
     * Maximum size of the player's party, lobby, or group.
     * <br>Example: 5
     */
    public int partyMax;

    /**
     * Unique hashed string for Spectate and Join.
     * Required to enable match interactive buttons in the user's presence.
     * <br>Example: "MmhuZToxMjMxMjM6cWl3amR3MWlqZA=="
     *
     * <p><b>Maximum: 128 characters</b>
     */
    public String matchSecret;

    /**
     * Unique hashed string for Spectate button.
     * This will enable the "Spectate" button on the user's presence if whitelisted.
     * <br>Example: "MTIzNDV8MTIzNDV8MTMyNDU0"
     *
     * <p><b>Maximum: 128 characters</b>
     */
    public String joinSecret;

    /**
     * Unique hashed string for chat invitations and Ask to Join.
     * This will enable the "Ask to Join" button on the user's presence if whitelisted.
     * <br>Example: "MTI4NzM0OjFpMmhuZToxMjMxMjM="
     *
     * <p><b>Maximum: 128 characters</b>
     */
    public String spectateSecret;
}
