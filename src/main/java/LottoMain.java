import domain.*;

import java.util.List;
import java.util.stream.Collectors;

import view.InputView;
import view.OutputView;

public class LottoMain {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        LottoTickets lottoTickets = buyLottos();
        outputView.printUserLottos(extractLottoNumbersPerTicket(lottoTickets));

        outputView.printWinningNumbersGuide();
        String winningNumbers = inputView.inputString();
        outputView.printBonusBallGuide();
        int bonusBall = inputView.inputInt();

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusBall);
        noticeResult(winningLotto, lottoTickets);
    }

    private static List<List<Integer>> extractLottoNumbersPerTicket(LottoTickets lottoTickets) {
        return lottoTickets.getLottoTicketList()
                .stream()
                .map(LottoTicket::getLottoNumbers)
                .collect(Collectors.toList());
    }

    private static LottoTickets buyLottos() {
        outputView.printGameGuide();
        int money = inputView.inputInt();
        inputView.inputString();

        LottoStore lottoStore = new LottoStore(money);
        outputView.printLottoCount(lottoStore.getLottoCount());

        return lottoStore.getLottoTickets(new LottoNumberGenerator());
    }

    private static void noticeResult(WinningLotto winningLotto, LottoTickets lottoTickets) {
        LottoGame lottoGame = new LottoGame(winningLotto, lottoTickets);
        outputView.printStatistics(lottoGame.getRank());
        outputView.printRevenue(lottoGame.calculateRevenuePercent());
    }
}
