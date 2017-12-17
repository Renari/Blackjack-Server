package com.arimil.blackjackserver;

import com.arimil.blackjackserver.packets.requests.BetRequest;
import com.arimil.blackjackserver.packets.requests.HitRequest;
import com.arimil.blackjackserver.packets.requests.HoldRequest;
import com.arimil.blackjackserver.packets.requests.LoginRequest;
import com.arimil.blackjackserver.packets.responses.*;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.minlog.Log;

class Network {
    static final int PORT = 50236;
    static final int TIMEOUT = 5000;

    static void register(EndPoint endpoint) {
        Log.debug("Registering classes");

        Kryo kryo = endpoint.getKryo();

        kryo.register(String[].class);

        // Register all requests
        kryo.register(LoginRequest.class);
        kryo.register(BetRequest.class);
        kryo.register(HitRequest.class);
        kryo.register(HoldRequest.class);

        // Register all responses
        kryo.register(LoginResponse.class);
        kryo.register(BetResponse.class);
        kryo.register(BustResponse.class);
        kryo.register(HitResponse.class);
        kryo.register(HoldResponse.class);
    }
}
