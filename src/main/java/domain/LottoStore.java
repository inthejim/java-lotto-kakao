package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoStore {

    private static final int LOTTO_PRICE = 1000;

    private final int lottoCount;

    public LottoStore(int money) {
        this.lottoCount = money / LOTTO_PRICE;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public LottoTickets getLottoTickets(NumberGenerator numberGenerator) {
        return new LottoTickets(IntStream.range(0, lottoCount)
                .mapToObj(number -> new LottoTicket(numberGenerator.generateNumbers()))
                .collect(Collectors.toList()));
    }
}
