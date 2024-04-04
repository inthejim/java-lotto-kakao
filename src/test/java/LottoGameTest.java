import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LottoGame 단위 테스트")
class LottoGameTest {

    private final LottoTicket winningLottoTicket = new LottoTicket(changeToLottoNumberList(List.of(1, 2, 3, 4, 5, 6)));
    private final LottoNumber bonusNumber = new LottoNumber(7);
    private final WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusNumber);

    private static List<LottoNumber> changeToLottoNumberList(List<Integer> integerList) {
        return integerList.stream().map(LottoNumber::new).collect(Collectors.toList());
    }

    @Test
    void 로또번호_당첨_개수() {
        LottoTickets lottoTickets = new LottoTickets(List.of(
                new LottoTicket(changeToLottoNumberList(List.of(11, 12, 13, 14, 15, 16))),
                new LottoTicket(changeToLottoNumberList(List.of(1, 2, 3, 4, 7, 8)))
        ));


        LottoGame lottoGame = new LottoGame(winningLotto, lottoTickets);
        Map<LottoPrice, Integer> result = lottoGame.getRank();

        assertThat(result).containsEntry(LottoPrice.NOTHING, 1);
        assertThat(result).containsEntry(LottoPrice.FOURTH, 1);
    }

    @Test
    void 로또번호_수익률() {
        LottoTickets lottoTickets = new LottoTickets(List.of(
                new LottoTicket(changeToLottoNumberList(List.of(11, 12, 13, 14, 15, 16))),
                new LottoTicket(changeToLottoNumberList(List.of(1, 2, 3, 7, 8, 9)))
        ));

        LottoGame lottoGame = new LottoGame(winningLotto, lottoTickets);
        float result = lottoGame.calculateRevenuePercent();

        assertThat(result).isCloseTo(2.5f, within(0.5f));
    }
}
