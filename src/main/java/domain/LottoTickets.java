package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoTickets {

    private static final int LOTTO_PRICE = 1000;
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public Map<LottoPrice, Integer> calculateRank(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .collect(Collectors.toMap(
                        winningLotto::calculatePrize, lottoTicket -> 1, Integer::sum));
    }

    public Integer purchaseAmount() {
        return lottoTickets.size() * LOTTO_PRICE;
    }

    public List<LottoTicket> getLottoTicketList() {
        return Collections.unmodifiableList(lottoTickets);
    }


}
