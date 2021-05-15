package me.mariobob.MinecraftDiscordBot;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.mariobob.MinecraftDiscordBot.DiscordEvents.MessageSendEvent;
import me.mariobob.MinecraftDiscordBot.DiscordEvents.ReadyEvent;
import me.mariobob.MinecraftDiscordBot.MinecraftEvents.*;
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
    public JDA discordBot;

    @Getter @Setter
    public Guild discordServer;

    @SneakyThrows
    @Override
    public void onEnable() {
        saveDefaultConfig();
        System.out.println("Plugin Is Ready!");
        String discordToken = getConfig().getString("DISCORD_TOKEN");
        this.discordBot = JDABuilder.createDefault(discordToken)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES)
                .build();
        getServer().getPluginManager().registerEvents(new PlayerJoinServerEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveServerEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerChatEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerGameDeathEvent(this), this);
// TODO: Fix with nms?        getServer().getPluginManager().registerEvents(new PlayerAchievementGetEvent(this), this);
        setBotStatus();
        this.discordBot.addEventListener(new ReadyEvent(this));
        this.discordBot.addEventListener(new MessageSendEvent(this));
        EnumSet<Message.MentionType> deny = EnumSet.of(Message.MentionType.EVERYONE, Message.MentionType.HERE);
        MessageAction.setDefaultMentions(EnumSet.complementOf(deny)); // disables @everyone and @here messages from mc to discord
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
        discordBot.shutdown();
    }

    public void setBotStatus(){
        discordBot.getPresence().setActivity(Activity.of(Activity.ActivityType.WATCHING, "Minecraft!"));
    }

    public TextChannel returnOrCreate(String name) {
        List<TextChannel> matches = discordBot.getTextChannelsByName(name, true);
        TextChannel channel;

        if (matches.size() == 0) {
            List<Category> categories = discordBot.getCategoriesByName(getDiscordCategoryName(), true);
            Category category;

            if (categories.size() == 0) {
                if (discordServer == null) {
                    return null;
                }

                category = discordServer.createCategory(getDiscordCategoryName()).complete();
            } else {
                category = categories.get(0);
            }

            channel = category.createTextChannel(name).complete();
        } else {
            channel = matches.get(0);
        }

        return channel;
    }


    private String getConfig(String key, String dValue) {
        String name = getConfig().getString(key);
        if (name == null) {
            return dValue;
        } else {
            return name;
        }
    }

    public String getMinecraftDiscordChannelName() {
        return getConfig("chat-channel-name", "minecraft");
    }

    public String getDiscordCategoryName() {
        return getConfig("category-name", "minecraft");
    }
}
