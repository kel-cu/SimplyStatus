package og.__kel_.simplystatus.api;

import og.__kel_.simplystatus.Main;
import og.__kel_.simplystatus.api.entity.addonEntity;

public class Register {
    public Register(addonEntity information, String address){
        Main.addonsServers.put(address, information);
    }
}
