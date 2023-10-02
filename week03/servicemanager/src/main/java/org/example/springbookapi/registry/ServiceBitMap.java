package org.example.springbookapi.registry;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;

public class ServiceBitMap {
    BitSet bitmap;

    public ServiceBitMap() {
        bitmap = new BitSet(320000);
    }

    public ServiceBitMap(BitSet b) {
        bitmap = b;
    }

    // Sets a bit at given position
    void set(int pos) {
        bitmap.set(pos);
    }

    public List<String> toList(Map<Integer, String> registered_services) {
        List<String> list = new ArrayList<>();
        for (int i = bitmap.nextSetBit(0); i != -1; i = bitmap.nextSetBit(i + 1)) {
            list.add(registered_services.get(i));
        }
        return list;
    }

    public ServiceBitMap intersection(ServiceBitMap intersectWith) {
        BitSet result = new BitSet(320000);
        result.set(0, 320000, true);
        BitSet[] sets = {this.bitmap, intersectWith.bitmap};
        for (BitSet set : sets) {
            result.and(set);
        }
        return new ServiceBitMap(result);
    }
}
