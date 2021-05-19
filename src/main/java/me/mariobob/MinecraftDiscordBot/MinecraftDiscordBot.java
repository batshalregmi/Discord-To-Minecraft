package me.mariobob.MinecraftDiscordBot;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.mariobob.MinecraftDiscordBot.DiscordEvents.MessageSendEvent;
import me.mariobob.MinecraftDiscordBot.DiscordEvents.ReadyEvent;
import me.mariobob.MinecraftDiscordBot.MinecraftEvents.PlayerChatEvent;
import me.mariobob.MinecraftDiscordBot.MinecraftEvents.PlayerGameDeathEvent;
import me.mariobob.MinecraftDiscordBot.MinecraftEvents.PlayerJoinServerEvent;
import me.mariobob.MinecraftDiscordBot.MinecraftEvents.PlayerLeaveServerEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

public final class MinecraftDiscordBot extends JavaPlugin {
    @Getter
    public JDA dBot;

    @Getter @Setter
    public Guild dServer;

    @SneakyThrows
    @Override
    public void onEnable() {
        saveDefaultConfig();
        String discordToken = getConfig().getString("DISCORD_TOKEN");
        this.dBot = JDABuilder.createDefault(discordToken)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)
                .build();
        getLogger().info("Initializing Minecraft events");
        getServer().getPluginManager().registerEvents(new PlayerJoinServerEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveServerEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerChatEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerGameDeathEvent(this), this);
// TODO: Fix with nms?        getServer().getPluginManager().registerEvents(new PlayerAchievementGetEvent(this), this);
        getLogger().info("Initializing Discord Bot");
        setBotStatus();
        this.dBot.addEventListener(new ReadyEvent(this));
        this.dBot.addEventListener(new MessageSendEvent(this));
        EnumSet<Message.MentionType> deny = EnumSet.of(Message.MentionType.EVERYONE, Message.MentionType.HERE);
        MessageAction.setDefaultMentions(EnumSet.complementOf(deny)); // disables @everyone and @here messages from mc to discord
        getLogger().info("Plugin is fully loaded");
    }

    @Override
    public void onDisable() {
        TextChannel textChannel = returnOrCreate(getMinecraftDiscordChannelName());
        if(textChannel != null){
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            EmbedBuilder em = new EmbedBuilder();
            em.setDescription("Server is Offline! :x:");
            em.setColor(Color.RED);
            em.setFooter(formatter.format(date));
            textChannel.sendMessage(em.build()).queue();
        }
        dBot.shutdown();
    }

    public void setBotStatus(){
        dBot.getPresence().setActivity(Activity.of(Activity.ActivityType.WATCHING, getConfig("discord-bot-presence")));
    }

    public TextChannel returnOrCreate(String name) {
        List<TextChannel> m = dBot.getTextChannelsByName(name, true); // get discord channels by the name
        TextChannel ch;
        if (m.size() == 0) {
            List<Category> cat = dBot.getCategoriesByName(getDiscordCategoryName(), true);
            Category c;
            if (cat.size() == 0) {
                if (dServer == null) {
                    return null;
                }
                c = dServer.createCategory(getDiscordCategoryName()).complete(); // create category if it doesn't exist
            } else {
                c = cat.get(0);
            }
            ch = c.createTextChannel(name).complete(); // create text channel if it doesn't exist
        } else {
            ch = m.get(0);
        }
        return ch; // return the channel
    }

    private String getConfig(String key) {
        String name = getConfig().getString(key);
        if (name == null) {
            getLogger().severe("Check the config.yml and check to make sure everything is filled out! Using the default minecraft name");
            return "minecraft";
        } else {
            return name;
        }
    }

    public String getMinecraftDiscordChannelName() {
        return getConfig("chat-channel-name");
    }

    public String timestamp(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public String getDiscordCategoryName() {
        return getConfig("category-name");
    }
}
