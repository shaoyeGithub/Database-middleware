package com.cbs.dataroute;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
/**
 * @author : TerryZhang
 * @description：hint
 * @date : 2020/2/10
 */

public final class ModuloHintShardingAlgorithm implements HintShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames, final HintShardingValue<Long> shardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames) {
            for (Long value : shardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value % 2))) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}