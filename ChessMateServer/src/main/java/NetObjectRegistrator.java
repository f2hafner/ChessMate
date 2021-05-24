import NetObjects.createSessionRequest;
import NetObjects.createSessionResponse;
import com.esotericsoftware.kryo.Kryo;

public class NetObjectRegistrator {
    public static void register(Kryo k){
        k.register(createSessionRequest.class);
        k.register(createSessionResponse.class);
    }
}
