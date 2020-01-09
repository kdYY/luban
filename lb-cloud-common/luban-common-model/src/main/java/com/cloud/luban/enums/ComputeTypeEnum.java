package com.cloud.luban.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ComputeTypeEnum {

    LAUNCH(1, "launchPlugin", "按命中法数"),

    PART(2, "partPlugin", "按命中部位"),

    TIME(3, "timePlugin", "按命中时间");

    /*
    1 按命中法数，2 按命中部位，3 按命中时间
     */
    private Integer type;
    private String pluginId;
    private String name;

    public static List<String> getPlugin() {
        List<String> pluginId = new ArrayList<>();
        pluginId.add(ComputeTypeEnum.LAUNCH.getPluginId());
        pluginId.add(ComputeTypeEnum.PART.getPluginId());
        pluginId.add(ComputeTypeEnum.TIME.getPluginId());
        return pluginId;
    }
}
