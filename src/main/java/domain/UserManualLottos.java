package domain;

import java.util.ArrayList;
import java.util.List;

public class UserManualLottos {

    List<LottoTicket> lottoTickets;

    UserManualLottos() {
        this.lottoTickets = new ArrayList<>();
    }

    public static void validateManualCount(int lottoCount, int manualCount) {
        if (manualCount > lottoCount) {
            throw new IllegalArgumentException("구입할 수 있는 로또의 매수를 초과하였습니다.");
        }
        if (manualCount < 0) {
            throw new IllegalArgumentException("양수를 입력해주세요.");
        }
    }

    public void addManualLotto(String Lotto) {
        lottoTickets.add(new LottoTicket(Lotto));
    }

    public LottoTickets getManualLottos() {
        return new LottoTickets(List.copyOf(lottoTickets));
    }
}
