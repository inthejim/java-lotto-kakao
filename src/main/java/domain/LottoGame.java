package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;

    private final LottoTickets lottoTickets;
    private final Map<LottoPrice, Integer> lottoResult;

    public LottoGame(WinningLotto winningLotto, LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets;
        this.lottoResult = lottoTickets.calculateRank(winningLotto);
    }

    public Map<LottoPrice, Integer> getRank() {
        return Collections.unmodifiableMap(lottoResult);
    }

    public float calculateRevenue() {
        int revenue = lottoResult.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        return (float) revenue / (LOTTO_PRICE * lottoTickets.getSize());
    }
}
