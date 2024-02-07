package com.mhw.mhwapi;

import java.util.*;

public class TestClass {

    public static void main(String args[]) {
        List<String> list = Arrays.asList("normal1_clip","normal1_rapid","normal1_recoil","normal1_reload","normal2_clip","normal2_rapid","normal2_recoil","normal2_reload","normal3_clip","normal3_rapid","normal3_recoil","normal3_reload","pierce1_clip","pierce1_rapid","pierce1_recoil","pierce1_reload","pierce2_clip","pierce2_rapid","pierce2_recoil","pierce2_reload","pierce3_clip","pierce3_rapid","pierce3_recoil","pierce3_reload","spread1_clip","spread1_rapid","spread1_recoil","spread1_reload","spread2_clip","spread2_rapid","spread2_recoil","spread2_reload","spread3_clip","spread3_rapid","spread3_recoil","spread3_reload","sticky1_clip","sticky1_rapid","sticky1_recoil","sticky1_reload","sticky2_clip","sticky2_rapid","sticky2_recoil","sticky2_reload","sticky3_clip","sticky3_rapid","sticky3_recoil","sticky3_reload","cluster1_clip","cluster1_rapid","cluster1_recoil","cluster1_reload","cluster2_clip","cluster2_rapid","cluster2_recoil","cluster2_reload","cluster3_clip","cluster3_rapid","cluster3_recoil","cluster3_reload","recover1_clip","recover1_rapid","recover1_recoil","recover1_reload","recover2_clip","recover2_rapid","recover2_recoil","recover2_reload","poison1_clip","poison1_rapid","poison1_recoil","poison1_reload","poison2_clip","poison2_rapid","poison2_recoil","poison2_reload","paralysis1_clip","paralysis1_rapid","paralysis1_recoil","paralysis1_reload","paralysis2_clip","paralysis2_rapid","paralysis2_recoil","paralysis2_reload","sleep1_clip","sleep1_rapid","sleep1_recoil","sleep1_reload","sleep2_clip","sleep2_rapid","sleep2_recoil","sleep2_reload","exhaust1_clip","exhaust1_rapid","exhaust1_recoil","exhaust1_reload","exhaust2_clip","exhaust2_rapid","exhaust2_recoil","exhaust2_reload","flaming_clip","flaming_rapid","flaming_recoil","flaming_reload","water_clip","water_rapid","water_recoil","water_reload","freeze_clip","freeze_rapid","freeze_recoil","freeze_reload","thunder_clip","thunder_rapid","thunder_recoil","thunder_reload","dragon_clip","dragon_rapid","dragon_recoil","dragon_reload","slicing_clip","slicing_rapid","slicing_recoil","slicing_reload","wyvern_clip","wyvern_reload","demon_clip","demon_recoil","demon_reload","armor_clip","armor_recoil","armor_reload","tranq_clip","tranq_recoil","tranq_reload");
        LinkedHashMap<String, String> mapResult = new LinkedHashMap<>();
        for (String a : list) {
            String[] splits = a.split("_");
            StringBuilder result = new StringBuilder(splits[0]);
            if (splits.length > 1) {
                for (int i = 1; i < splits.length; i++)
                {
                    String s = splits[i];
                    if (!Character.isDigit(s.charAt(0))) {
                        s = s.substring(0, 1).toUpperCase() + s.substring(1);
                    }
                    result.append(s);
                }
            }
            if (result.toString().contains("Clip")) {
                mapResult.put(result.toString(), "Integer");
            } else if (result.toString().contains("Rapid")) {
                mapResult.put(result.toString(), "Boolean");
            } else if (result.toString().contains("Recoil")) {
                mapResult.put(result.toString(), "Integer");
            } else if (result.toString().contains("Reload")) {
                mapResult.put(result.toString(), "String");
            } else {
                mapResult.put(result.toString(), "String");
            }
        }
        for (String key: mapResult.keySet()) {
            System.out.println("private " + mapResult.get(key) + " " + key + ";");
        }
    }
}
