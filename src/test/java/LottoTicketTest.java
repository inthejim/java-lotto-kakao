import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.LottoNumber;
import domain.LottoTicket;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("LottoTicket 단위 테스트")
public class LottoTicketTest {

    private LottoTicket lottoTicket = new LottoTicket(changeToLottoNumberList(List.of(1, 2, 3, 4, 5, 6)));

    private static List<LottoNumber> changeToLottoNumberList(List<Integer> integerList) {
        return integerList.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Test
    void 로또티켓_생성() {
        assertThat(lottoTicket).isNotNull();
    }

    @Test
    void 로또티켓_번호중복() {
        assertThatThrownBy(() -> new LottoTicket(changeToLottoNumberList(List.of(1, 1, 2, 3, 4, 5))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또티켓_번호_6개() {
        assertThatThrownBy(() -> new LottoTicket(changeToLottoNumberList(List.of(1, 2, 3, 4, 5))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또티켓_보너스번호_포함_확인() {
        assertThat(lottoTicket.contains(new LottoNumber(6))).isTrue();
    }

    @Test
    void 로또티켓_다른티켓_비교() {
        LottoTicket otherLottoTicket = new LottoTicket(changeToLottoNumberList(List.of(2, 3, 4, 5, 6, 7)));

        assertThat(lottoTicket.matchCount(otherLottoTicket)).isEqualTo(5);
    }
}
