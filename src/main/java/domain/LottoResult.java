package domain;

import java.util.Collections;
import java.util.Map;

public class LottoResult {

    private final Map<LottoPrice, Integer> lottoResult;

    public LottoResult(WinningLotto winningLotto, LottoTickets lottoTickets) {
        this.lottoResult = lottoTickets.calculateRank(winningLotto);
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
