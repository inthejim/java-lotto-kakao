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

    public LottoTickets buyRandomLottoTickets(NumberGenerator numberGenerator, int manulCount) {
        return new LottoTickets(IntStream.range(0, lottoCount - manulCount)
                .mapToObj(number -> new LottoTicket(numberGenerator.generateNumbers()))
                .collect(Collectors.toList()));
    }

    public LottoTickets buyManualLottoTickets(List<String> userInput) {
        UserManualLottos manualLottos = new UserManualLottos();

        userInput.stream().forEach(manualLotto ->
                manualLottos.addManualLotto(manualLotto));

        return manualLottos.getManualLottos();
    }

    public LottoTickets buyLottoTickets(NumberGenerator numberGenerator, List<String> userInput) {
        LottoTickets lottoTickets = buyManualLottoTickets(userInput);

        return lottoTickets.joinTickets(buyRandomLottoTickets(numberGenerator, userInput.size()));
    }
}
