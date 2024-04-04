package domain;

import java.util.Collections;
import java.util.Map;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;

    private final LottoTickets lottoTickets;
    private final LottoResult lottoResult;

    public LottoGame(WinningLotto winningLotto, LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets;
        this.lottoResult = new LottoResult(winningLotto, lottoTickets);
    }

    public Map<LottoPrice, Integer> getRank() {
        return lottoResult.getLottoResult();
    }

    public float calculateRevenuePercent() {
        return lottoResult.calculateRevenue() / (LOTTO_PRICE * lottoTickets.getSize());
    }
}
