package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public Map<LottoPrice, Integer> calculateRank(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .collect(Collectors.toMap(
                        winningLotto::calculatePrize, lottoTicket -> 1, Integer::sum));
    }

    public Integer getSize() {
        return lottoTickets.size();
    }

    public List<LottoTicket> getLottoTicketList() {
        return Collections.unmodifiableList(lottoTickets);
    }


}
