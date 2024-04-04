package domain;

import java.util.Map;

public class LottoGame {

    private final LottoTickets lottoTickets;
    private final LottoResult lottoResult;

    public LottoGame(WinningLotto winningLotto, LottoTickets lottoTickets) {
        this.lottoTickets = lottoTickets;
        this.lottoResult = new LottoResult(lottoTickets.calculateRank(winningLotto));
    }

    public Map<LottoPrice, Integer> getRank() {
        return lottoResult.getLottoResult();
    }

    public float calculateRevenuePercent() {
        return lottoResult.calculateRevenue() / lottoTickets.purchaseAmount();
    }
}
