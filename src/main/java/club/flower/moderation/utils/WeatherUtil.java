package club.flower.moderation.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;

/*
 *  @Author: Emmy
 *  @Project: Moderation
 *  @Date: 16/03/2024
 */

public class WeatherUtil {
    public static void disableWeather(World world) {
        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(0);
    }

    public static void disableWeatherInAllWorlds() {
        for (World world : Bukkit.getWorlds()) {
            disableWeather(world);
        }
    }
}