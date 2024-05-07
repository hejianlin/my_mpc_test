package org.hejianlin.my_mpc_test.proxy_demo;

public class IronManVIPMovie implements VIPMovie{

    @Override
    public void vipPlay() {
        System.out.println("VI影厅正在播放的电影是《钢铁侠》");
    }

}
