package org.hejianlin.my_mpc_test.proxy_demo;

public class CaptainAmericaMovie implements Movie {
    @Override
    public void play() {
        System.out.println("普通影厅正在播放的电影是《美国队长》");
    }
}
