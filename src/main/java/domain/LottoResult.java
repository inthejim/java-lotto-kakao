package domain;

import java.util.Collections;
import java.util.Map;

public class LottoResult {

    private final Map<LottoPrice, Integer> lottoResult;

    public LottoResult(Map<LottoPrice, Integer> lottoResult) {
        this.lottoResult = lottoResult;
    }

    public float calculateRevenue() {
        return lottoResult.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<LottoPrice, Integer> getLottoResult() {
        return Collections.unmodifiableMap(lottoResult);
    }
}
