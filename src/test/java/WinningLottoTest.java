import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.LottoNumber;
import domain.LottoPrice;
import domain.LottoTicket;
import domain.WinningLotto;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    public static final LottoTicket WINNING_LOTTO_TICKET = new LottoTicket(changeToLottoNumberList(List.of(1, 2, 3, 4, 5, 6)));

    private static List<LottoNumber> changeToLottoNumberList(List<Integer> integerList) {
        return integerList.stream().map(LottoNumber::new).collect(Collectors.toList());
    }

    @Test
    void 보너스번호_로또번호_중복불가() {
        LottoNumber bonusNumber = new LottoNumber(6);

        assertThrows(IllegalArgumentException.class,
                () -> new WinningLotto(WINNING_LOTTO_TICKET, bonusNumber));
    }

    @Test
    void 일치하는_로또번호_계산() {
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(WINNING_LOTTO_TICKET, bonusNumber);

        LottoPrice lottoPrice = winningLotto.calculatePrize(
                new LottoTicket(changeToLottoNumberList(List.of(1, 2, 3, 11, 12, 13))));

        assertThat(lottoPrice).isEqualTo(LottoPrice.FIFTH);
    }

    @Test
    void 일치하는_로또번호_계산_2() {
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(WINNING_LOTTO_TICKET, bonusNumber);

        LottoPrice lottoPrice = winningLotto.calculatePrize(
                new LottoTicket(changeToLottoNumberList(List.of(1, 2, 3, 4, 7, 8))));

        assertThat(lottoPrice).isEqualTo(LottoPrice.FOURTH);
    }

    @Test
    void 불일치하는_로또번호_계산() {
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(WINNING_LOTTO_TICKET, bonusNumber);

        LottoPrice lottoPrice = winningLotto.calculatePrize(
                new LottoTicket(changeToLottoNumberList(List.of(1, 2, 11, 12, 13, 14))));

        assertThat(lottoPrice).isEqualTo(LottoPrice.NOTHING);
    }
}
