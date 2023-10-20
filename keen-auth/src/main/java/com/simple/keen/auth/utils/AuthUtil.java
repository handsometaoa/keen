package com.simple.keen.auth.utils;

import cn.hutool.core.util.RandomUtil;

public class AuthUtil {

    public static String generateRandomNickname() {
        String[] ADJECTIVES = {"快乐的", "聪明的", "勇敢的", "可爱的", "善良的"};
        String[] NOUNS = {"小猫", "小狗", "小兔子", "小鸟", "小鱼"};
        String adjective = RandomUtil.randomEle(ADJECTIVES);
        String noun = RandomUtil.randomEle(NOUNS);

        return adjective + noun;
    }
}
