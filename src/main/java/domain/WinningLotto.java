package domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WinningLotto {

    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        validateNotDuplicated(winningLottoTicket, bonusNumber);

        this.winningLottoTicket = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    public WinningLotto(String winningNumbers, int bonusBall) {
        this(new LottoTicket(Arrays.stream(winningNumbers.split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList())), new LottoNumber(bonusBall));
    }

    private void validateNotDuplicated(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        if (winningLottoTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 중복될 수 없습니다.");
        }
    }

    public LottoPrice calculatePrize(LottoTicket lottoTicket) {
        long matchCount = winningLottoTicket.matchCount(lottoTicket);
        boolean isBonusMatch = lottoTicket.contains(bonusNumber);

        return Arrays.stream(LottoPrice.values())
            .filter(lottoPrice -> lottoPrice.matchPrice(matchCount, isBonusMatch))
            .findAny()
            .orElse(LottoPrice.NOTHING);
    }
}
