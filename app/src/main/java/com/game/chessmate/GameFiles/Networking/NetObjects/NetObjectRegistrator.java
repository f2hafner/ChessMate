package com.game.chessmate.GameFiles.Networking.NetObjects;

import com.esotericsoftware.kryo.Kryo;
/**
 * registers the Request and Response Classes to kryonet
 */
public class NetObjectRegistrator {
    public static void register(Kryo k){
        k.register(createSessionRequest.class);
        k.register(createSessionResponse.class);
    }
}
